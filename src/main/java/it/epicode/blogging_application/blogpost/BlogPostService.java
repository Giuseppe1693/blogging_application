package it.epicode.blogging_application.blogpost;

import it.epicode.blogging_application.author.Author;
import it.epicode.blogging_application.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepo;
    private final AuthorRepository authorRepo;

    public List<BlogPost> getAll() {
        return blogPostRepo.findAll();
    }

    public BlogPost getById(Long id) {
        return blogPostRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BlogPost non trovato"));
    }

    public BlogPost create(BlogPostDTO dto) {
        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato"));

        BlogPost post = BlogPost.builder()
                .categoria(dto.getCategoria())
                .titolo(dto.getTitolo())
                .contenuto(dto.getContenuto())
                .cover("https://picsum.photos/200/300")
                .tempoDiLettura(calcolaTempoDiLettura(dto.getContenuto()))
                .author(author)
                .build();

        return blogPostRepo.save(post);
    }

    public BlogPost update(Long id, BlogPostDTO dto) {
        BlogPost existing = getById(id);
        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato"));

        existing.setCategoria(dto.getCategoria());
        existing.setTitolo(dto.getTitolo());
        existing.setContenuto(dto.getContenuto());
        existing.setTempoDiLettura(calcolaTempoDiLettura(dto.getContenuto()));
        existing.setAuthor(author);
        return blogPostRepo.save(existing);
    }

    public void delete(Long id) {
        blogPostRepo.deleteById(id);
    }

    private int calcolaTempoDiLettura(String contenuto) {
        int parole = contenuto.split("\\s+").length;
        return Math.max(1, parole / 200);
    }
}
