package virgilistrate.U5L7.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import virgilistrate.U5L7.entities.BlogPost;
import virgilistrate.U5L7.payloads.NewBlogPostPayload;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {

    private final List<BlogPost> blogPosts = new ArrayList<>();

    @GetMapping
    public List<BlogPost> getAll() {
        return blogPosts;
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable long id) {
        return blogPosts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public BlogPost create(@RequestBody NewBlogPostPayload body) {

        BlogPost newPost = new BlogPost(
                body.getCategoria(),
                body.getTitolo(),
                body.getContenuto(),
                body.getTempoDiLettura()
        );

        blogPosts.add(newPost);
        return newPost;
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable long id, @RequestBody NewBlogPostPayload body) {

        BlogPost found = getById(id);

        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());

        return found;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        BlogPost found = getById(id);
        blogPosts.remove(found);
    }
}
