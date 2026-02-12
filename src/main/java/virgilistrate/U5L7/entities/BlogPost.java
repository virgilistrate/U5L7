package virgilistrate.U5L7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue
    private UUID id;

    private String categoria;
    private String titolo;
    private String cover;

    @Column(columnDefinition = "TEXT")
    private String contenuto;

    private int tempoDiLettura;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.cover == null) this.cover = "https://picsum.photos/seed/" + UUID.randomUUID() + "/200/300";
    }
}
