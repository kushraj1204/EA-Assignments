package repository;

import domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /*@Query("UPDATE Book b set b.locationCode=CONCAT(:prefix,b.locationCode)")
    @Modifying
    @Transactional
    void updateLocationCodesWithPrefix(String prefix);

    @Modifying
    @Transactional
    @Query("DELETE Book b where b.publicationYear < :year")
    void deleteOldBooks(int year);

    @Query("Select b.id from Book b")
    List<Long> findAllIds();*/
}
