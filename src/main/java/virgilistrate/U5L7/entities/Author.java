package virgilistrate.U5L7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String cognome;

    @Column(unique = true)
    private String email;

    private LocalDate dataDiNascita;

    private String avatar;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    private List<BlogPost> blogPosts;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.avatar == null) this.avatar = "https://ui-avatars.com/api/?name=" + this.nome + "+" + this.cognome;
    }
}
