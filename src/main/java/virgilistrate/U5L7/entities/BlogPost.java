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
    private LocalDateTime createdAt;

    public BlogPost(String categoria, String titolo, String contenuto, int tempoDiLettura) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;

        this.createdAt = LocalDateTime.now();

        Random rndm = new Random();
        this.id = rndm.nextInt(1, 1000);

        this.cover = "https://picsum.photos/seed/" + this.id + "/200/300";
    }
}
