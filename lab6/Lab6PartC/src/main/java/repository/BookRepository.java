package repository;

import domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("UPDATE Book b set b.locationCode=CONCAT(:prefix,b.locationCode)")
    @Modifying
    @Transactional
    void updateLocationCodesWithPrefix(String prefix);

    @Modifying
    @Transactional
    @Query("DELETE Book b where b.publicationYear < :year")
    void deleteOldBooks(int year);

    @Query("Select b.id from Book b")
    List<Long> findAllIds();
}
