package virgilistrate.U5L7.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewAuthorPayload {

    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita; // formato YYYY-MM-DD
}
