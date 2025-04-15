package it.epicode.blogging_application.blogpost;

import it.epicode.blogging_application.author.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String titolo;
    private String cover;
    @Lob
    private String contenuto;
    private int tempoDiLettura;

    @ManyToOne
    private Author author;
}

