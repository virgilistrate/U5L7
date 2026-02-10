package virgilistrate.U5L7.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewBlogPostPayload {

    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
}
