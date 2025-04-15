package it.epicode.blogging_application.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepo;

    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    public Author getById(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autore non trovato"));
    }

    public Author create(AuthorDTO dto) {
        Author author = Author.builder()
                .nome(dto.getNome())
                .cognome(dto.getCognome())
                .email(dto.getEmail())
                .dataDiNascita(dto.getDataDiNascita())
                .avatar("https://ui-avatars.com/api/?name=" + dto.getNome() + "+" + dto.getCognome())
                .build();
        return authorRepo.save(author);
    }

    public Author update(Long id, AuthorDTO dto) {
        Author author = getById(id);
        author.setNome(dto.getNome());
        author.setCognome(dto.getCognome());
        author.setEmail(dto.getEmail());
        author.setDataDiNascita(dto.getDataDiNascita());
        author.setAvatar("https://ui-avatars.com/api/?name=" + dto.getNome() + "+" + dto.getCognome());
        return authorRepo.save(author);
    }

    public void delete(Long id) {
        authorRepo.deleteById(id);
    }
}
