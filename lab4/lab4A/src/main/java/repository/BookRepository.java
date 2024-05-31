package repository;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}
