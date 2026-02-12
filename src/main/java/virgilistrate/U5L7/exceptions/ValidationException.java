package virgilistrate.U5L7.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("Payload non valido!");
        this.errorsMessages = errorsMessages;
    }

    public List<String> getErrorsMessages() {
        return errorsMessages;
    }
}
