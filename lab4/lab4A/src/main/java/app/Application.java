package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.DepartmentRepository;
import repository.PassengerRepository;
import repository.SchoolRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	SchoolRepository schoolRepository;



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Employee emp1=new Employee("Anoj");
		Employee emp2=new Employee("Kush");
		Department dept=new Department("Computer Science");
		emp1.setDepartment(dept);
		emp2.setDepartment(dept);
		dept.setEmployees(List.of(emp1,emp2));
		departmentRepository.save(dept);

		List<Department> depts=departmentRepository.findAll();
		depts.forEach(System.out::println);

		System.out.println("\\n\\n\\n\\n");

		Book book=new Book("2456-8654","Dune:Prophecy","Frank Herbert");
		book.setPublisher(new Publisher("5-Star Publishers"));
		bookRepository.save(book);

		Book book1=new Book("2456-8658","Children of Dune","Frank Herbert");
		bookRepository.save(book1);

		List<Book> books=bookRepository.findAll();
		books.forEach(System.out::println);

		System.out.println("\\n\\n\\n\\n");

		Passenger passenger=new Passenger("Michael David Rosenberg");
		passenger.addFlight(new Flight("62632-212","Denver","Florida", LocalDate.now().plusMonths(1)));
		passenger.addFlight(new Flight("62632-215","Dallas","San Francisco", LocalDate.now().plusWeeks(3)));

		passengerRepository.save(passenger);
		List<Passenger> passengers=passengerRepository.findAll();
		passengers.forEach(System.out::println);

		System.out.println("\\n\\n\\n\\n");

		School school=new School("Maharishi International School");
		Student student1=new Student("617595","Kush Raj","Rimal");
		Student student2=new Student("617596","Anoj Kumar","Shrestha");
		school.setStudents(Map.of(student1.getStudentId(),student1,student2.getStudentId(),student2));

		schoolRepository.save(school);

		List<School> schools=schoolRepository.findAll();
		schools.forEach(System.out::println);
	}
}
