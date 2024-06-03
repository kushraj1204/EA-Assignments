package repositories;

import domain.Department;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
