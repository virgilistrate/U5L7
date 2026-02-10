package virgilistrate.U5L7.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
public class Author {

    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;
    private LocalDateTime createdAt;

    public Author(String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;

        this.createdAt = LocalDateTime.now();

        Random rndm = new Random();
        this.id = rndm.nextInt(1, 1000);

        this.avatar = "https://ui-avatars.com/api/?name=" + nome + "+" + cognome;
    }
}
