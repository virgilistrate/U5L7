package virgilistrate.U5L7.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorsWithListDTO {
    private String message;
    private LocalDateTime timestamp;
    private List<String> errorsList;
}
