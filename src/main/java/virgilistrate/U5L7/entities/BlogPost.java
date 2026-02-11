package virgilistrate.U5L7.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
public class BlogPost {

    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private long authorId; // relazione tramite id autore
    private LocalDateTime createdAt;

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura, long authorId) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.authorId = authorId;

        this.createdAt = LocalDateTime.now();

        Random rndm = new Random();
        this.id = rndm.nextInt(1, 1000);

        this.cover = "https://picsum.photos/seed/" + this.id + "/200/300";
    }
}
