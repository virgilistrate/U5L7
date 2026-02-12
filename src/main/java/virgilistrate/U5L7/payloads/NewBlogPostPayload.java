package virgilistrate.U5L7.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class NewBlogPostPayload {

    @NotBlank(message = "Categoria obbligatoria")
    private String categoria;

    @NotBlank(message = "Titolo obbligatorio")
    private String titolo;

    @NotBlank(message = "Contenuto obbligatorio")
    private String contenuto;

    @Min(value = 1, message = "tempoDiLettura deve essere almeno 1")
    private int tempoDiLettura;

    @NotNull(message = "authorId obbligatorio")
    private UUID authorId;
}
