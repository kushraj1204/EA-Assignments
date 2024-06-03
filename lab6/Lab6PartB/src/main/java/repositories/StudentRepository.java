package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kush
 */
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s join fetch s.department where s.department.name=:departmentName ")
    List<Student> findAllFromDepartment(String departmentName);

    @Query("select s from Student s join fetch s.grades g join fetch g.course c where c.name=:courseName ")
    List<Student> findAllTakingCourse(String courseName);


}
