package repository;

import domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface SchoolRepository extends JpaRepository<School,Long> {
}
