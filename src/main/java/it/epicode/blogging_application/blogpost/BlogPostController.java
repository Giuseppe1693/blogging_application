package it.epicode.blogging_application.blogpost;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/blogPosts")
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogPostService.getAll(pageable);
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

