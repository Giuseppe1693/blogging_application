package it.epicode.blogging_application.blogpost;

import it.epicode.blogging_application.author.AuthorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAll() {
        return blogPostService.getAll();
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable Long id) {
        return blogPostService.getById(id);
    }

    @PostMapping
    public ResponseEntity<BlogPost> create(@RequestBody @Valid BlogPostDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPostService.create(dto));
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable Long id, @RequestBody @Valid BlogPostDTO dto) {
        return blogPostService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        blogPostService.delete(id);
    }
}

