package virgilistrate.U5L7.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import virgilistrate.U5L7.entities.BlogPost;
import virgilistrate.U5L7.exceptions.BadRequestException;
import virgilistrate.U5L7.exceptions.NotFoundException;
import virgilistrate.U5L7.payloads.NewBlogPostPayload;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class BlogPostsService {

    private final List<BlogPost> blogPostsDB;
    private final AuthorsService authorsService;

    @Autowired
    public BlogPostsService(AuthorsService authorsService) {
        this.blogPostsDB = new ArrayList<>();
        this.authorsService = authorsService;
    }

    public BlogPost save(NewBlogPostPayload payload) {
        this.authorsService.findById(payload.getAuthorId());

        BlogPost newPost = new BlogPost(
                payload.getCategoria(),
                payload.getTitolo(),
                payload.getContenuto(),
                payload.getTempoDiLettura(),
                payload.getAuthorId()
        );

        this.blogPostsDB.add(newPost);
        log.info("Il blogpost con id " + newPost.getId() + " è stato salvato correttamente!");
        return newPost;
    }

    public List<BlogPost> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size <= 0) size = 10;
        if (page < 0) page = 0;

        List<BlogPost> sorted = new ArrayList<>(this.blogPostsDB);

        if (orderBy.equals("createdAt")) {
            sorted.sort(Comparator.comparing(BlogPost::getCreatedAt));
        } else if (orderBy.equals("titolo")) {
            sorted.sort(Comparator.comparing(BlogPost::getTitolo, String.CASE_INSENSITIVE_ORDER));
        } else {
            throw new BadRequestException("orderBy non valido!");
        }

        if (sortCriteria.equals("desc")) {
            List<BlogPost> reversed = new ArrayList<>();
            for (int i = sorted.size() - 1; i >= 0; i--) reversed.add(sorted.get(i));
            sorted = reversed;
        }

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, sorted.size());

        if (fromIndex >= sorted.size()) return new ArrayList<>();

        return sorted.subList(fromIndex, toIndex);
    }

    public BlogPost findById(long blogPostId) {
        return this.blogPostsDB.stream()
                .filter(p -> p.getId() == blogPostId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload payload) {
        BlogPost found = this.findById(blogPostId);

        this.authorsService.findById(payload.getAuthorId());

        found.setCategoria(payload.getCategoria());
        found.setTitolo(payload.getTitolo());
        found.setContenuto(payload.getContenuto());
        found.setTempoDiLettura(payload.getTempoDiLettura());
        found.setAuthorId(payload.getAuthorId());

        log.info("Il blogpost con id " + found.getId() + " è stato modificato correttamente!");
        return found;
    }

    public void findByIdAndDelete(long blogPostId) {
        BlogPost found = this.findById(blogPostId);
        this.blogPostsDB.remove(found);
        log.info("Il blogpost con id " + blogPostId + " è stato eliminato correttamente!");
    }
}
