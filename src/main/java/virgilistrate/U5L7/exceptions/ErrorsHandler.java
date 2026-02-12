package virgilistrate.U5L7.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import virgilistrate.U5L7.payloads.ErrorsDTO;
import virgilistrate.U5L7.payloads.ErrorsWithListDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationException(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsMessages());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException ex) {

        List<String> errorsList = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errorsList.add(err.getField() + ": " + err.getDefaultMessage());
        });

        return new ErrorsWithListDTO("Payload non valido!", LocalDateTime.now(), errorsList);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleGenericServerError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("C'è stato un errore, giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}
