package app;

import domain.Department;
import domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.DepartmentRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Employee emp1=new Employee("Anoj");
		Employee emp2=new Employee("Kush");
		Department dept=new Department("Computer Science");
		dept.setEmployees(List.of(emp1,emp2));
		departmentRepository.save(dept);

//		List<Department> depts=departmentRepository.findAll();
//		depts.forEach(System.out::println);


	}
}
