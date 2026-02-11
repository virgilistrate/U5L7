package virgilistrate.U5L7.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Risorsa con id " + id + " non trovata!");
    }
}
