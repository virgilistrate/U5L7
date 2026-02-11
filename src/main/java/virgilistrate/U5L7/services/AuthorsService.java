package virgilistrate.U5L7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virgilistrate.U5L7.entities.Author;
import virgilistrate.U5L7.exceptions.BadRequestException;
import virgilistrate.U5L7.exceptions.NotFoundException;
import virgilistrate.U5L7.payloads.NewAuthorPayload;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorsService {

    private final List<Author> authorsDB;

    @Autowired
    public AuthorsService() {
        this.authorsDB = new ArrayList<>();
    }

    public Author save(NewAuthorPayload payload) {
        this.authorsDB.stream()
                .filter(a -> a.getEmail().equals(payload.getEmail()))
                .findFirst()
                .ifPresent(a -> {
                    throw new BadRequestException("L'email " + a.getEmail() + " è già in uso!");
                });

        LocalDate dob;
        try {
            dob = LocalDate.parse(payload.getDataDiNascita());
        } catch (Exception ex) {
            throw new BadRequestException("dataDiNascita deve essere nel formato YYYY-MM-DD");
        }

        Author newAuthor = new Author(payload.getNome(), payload.getCognome(), payload.getEmail(), dob);
        this.authorsDB.add(newAuthor);

        log.info("L'autore con id " + newAuthor.getId() + " è stato salvato correttamente!");
        return newAuthor;
    }

    public List<Author> findAll() {
        return this.authorsDB;
    }

    public Author findById(long authorId) {
        return this.authorsDB.stream()
                .filter(a -> a.getId() == authorId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndUpdate(long authorId, NewAuthorPayload payload) {
        Author found = this.findById(authorId);

        if (!found.getEmail().equals(payload.getEmail())) {
            this.authorsDB.stream()
                    .filter(a -> a.getEmail().equals(payload.getEmail()))
                    .findFirst()
                    .ifPresent(a -> {
                        throw new BadRequestException("L'email " + a.getEmail() + " è già in uso!");
                    });
        }

        LocalDate dob;
        try {
            dob = LocalDate.parse(payload.getDataDiNascita());
        } catch (Exception ex) {
            throw new BadRequestException("dataDiNascita deve essere nel formato YYYY-MM-DD");
        }

        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setDataDiNascita(dob);
        found.setAvatar("https://ui-avatars.com/api/?name=" + found.getNome() + "+" + found.getCognome());

        log.info("L'autore con id " + found.getId() + " è stato modificato correttamente!");
        return found;
    }

    public void findByIdAndDelete(long authorId) {
        Author found = this.findById(authorId);
        this.authorsDB.remove(found);
        log.info("L'autore con id " + authorId + " è stato eliminato correttamente!");
    }
}
