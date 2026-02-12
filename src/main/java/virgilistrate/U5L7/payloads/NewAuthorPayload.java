package virgilistrate.U5L7.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NewAuthorPayload {

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Email non valida")
    private String email;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data Di Nascita deve essere nel formato YYYY-MM-DD")
    private String dataDiNascita;
}
