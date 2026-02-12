package virgilistrate.U5L7.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BlogPostResponseDTO {
    private UUID id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private LocalDateTime createdAt;

    private UUID authorId;
    private String authorNome;
    private String authorCognome;
}
