package it.epicode.blogging_application.blogpost;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostDTO {

    @NotBlank
    private String categoria;

    @NotBlank
    private String titolo;

    @NotBlank
    private String contenuto;

    @NotNull
    private Long authorId;
}

