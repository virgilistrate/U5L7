package virgilistrate.U5L7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virgilistrate.U5L7.entities.Author;

import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Author, UUID> {
}
