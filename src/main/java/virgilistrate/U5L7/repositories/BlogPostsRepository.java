package virgilistrate.U5L7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.U5L7.entities.BlogPost;

import java.util.UUID;

public interface BlogPostsRepository extends JpaRepository<BlogPost, UUID> {
}
