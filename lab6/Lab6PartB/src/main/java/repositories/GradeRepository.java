package repositories;

import domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface GradeRepository extends JpaRepository<Grade,Long> {
}
