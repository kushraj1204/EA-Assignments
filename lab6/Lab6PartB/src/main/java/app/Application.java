package app;

import domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.CourseRepository;
import repositories.DepartmentRepository;
import repositories.GradeRepository;
import repositories.StudentRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")

public class Application implements CommandLineRunner{

	final CourseRepository courseRepository;
	final StudentRepository studentRepository;
	final GradeRepository gradeRepository;
	final DepartmentRepository departmentRepository;

    public Application(CourseRepository courseRepository, StudentRepository studentRepository, GradeRepository gradeRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

/*		Department d1=new Department("Compro");
		Department d2=new Department("MBA");

		Course c1=new Course("MPP");
		Course c2=new Course("FPP");
		Course c3=new Course("Accounting");
		Course c4=new Course("Finance");

		departmentRepository.saveAll(Arrays.asList(d1,d2));


		courseRepository.saveAll(Arrays.asList(c1,c2,c3,c4));

		Grade grade1=new Grade("3.21");
		grade1.setCourse(c1);
		Grade grade2=new Grade("3.22");
		grade2.setCourse(c2);
		Grade grade3=new Grade("3.23");
		grade3.setCourse(c3);
		Grade grade4=new Grade("3.24");
		grade4.setCourse(c4);

		Grade grade5=new Grade("3.21");
		grade5.setCourse(c1);
		Grade grade6=new Grade("3.22");
		grade6.setCourse(c2);
		Grade grade7=new Grade("3.23");
		grade7.setCourse(c3);
		Grade grade8=new Grade("3.24");
		grade8.setCourse(c4);

		gradeRepository.saveAll(Arrays.asList(grade1,grade2,grade3,grade4,grade5,grade6,grade7,grade8));

		Student st1=new Student("Kush1","6175951");
		Student st2=new Student("Kush2","6175952");
		Student st3=new Student("Kush3","6175953");
		Student st4=new Student("Kush4","6175954");
		st1.setDepartment(d1);
		st1.setGrades(Arrays.asList(grade1,grade2));
		st2.setDepartment(d1);
		st2.setGrades(Arrays.asList(grade5,grade6));
		st3.setDepartment(d2);
		st3.setGrades(Arrays.asList(grade3,grade4));
		st4.setDepartment(d2);
		st4.setGrades(Arrays.asList(grade7,grade8));
		studentRepository.saveAll(Arrays.asList(st1,st2,st3,st4));*/

		List<Student> students = studentRepository.findAllFromDepartment("Compro");
		students.forEach(System.out::println);

		List<Student> studentsTakingCourse = studentRepository.findAllTakingCourse("Finance");
		studentsTakingCourse.forEach(System.out::println);


	}

}
