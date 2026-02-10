package virgilistrate.U5L7.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virgilistrate.U5L7.entities.Author;
import virgilistrate.U5L7.payloads.NewAuthorPayload;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final List<Author> authors = new ArrayList<>();

    @GetMapping
    public List<Author> getAll() {
        return authors;
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable long id) {
        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Author create(@RequestBody NewAuthorPayload body) {

        LocalDate dob;
        try {
            dob = LocalDate.parse(body.getDataDiNascita());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato data YYYY-MM-DD");
        }

        Author newAuthor = new Author(
                body.getNome(),
                body.getCognome(),
                body.getEmail(),
                dob
        );

        authors.add(newAuthor);
        return newAuthor;
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable long id, @RequestBody NewAuthorPayload body) {

        Author found = getById(id);

        LocalDate dob = LocalDate.parse(body.getDataDiNascita());

        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setDataDiNascita(dob);
        found.setAvatar("https://ui-avatars.com/api/?name=" + found.getNome() + "+" + found.getCognome());

        return found;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        Author found = getById(id);
        authors.remove(found);
    }
}
