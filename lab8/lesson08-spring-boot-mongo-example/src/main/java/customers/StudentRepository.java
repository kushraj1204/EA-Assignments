package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByName(String name);

    List<Student> findByPhone(String phone);

    @Query("{'address.city' : :#{#city}}")
    List<Student>  findStudentWithCity(@Param("city") String city);

    @Query("{'grade.courseName' : :#{#courseName}}")
    List<Student>  findStudentsWithCourse(@Param("courseName") String courseName);

    @Query("{'grade.courseName' : :#{#courseName}, 'grade.grade' : 'A+'}")
    List<Student>  findStudentsWithAPlusFor(@Param("courseName") String courseName);

}

