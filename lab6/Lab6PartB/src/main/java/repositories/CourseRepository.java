package repositories;

import domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface CourseRepository extends JpaRepository<Course,Long> {
}
