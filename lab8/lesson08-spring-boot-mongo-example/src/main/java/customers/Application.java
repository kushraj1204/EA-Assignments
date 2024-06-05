package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Address address=new Address("52557","Baker Street","London");
		Address address1=new Address("52557","Baker Street","Londons");
		Student student=new Student("52557","Kush Raj Rimal","8767809876",address);
		Student student1=new Student("52558","Anoj Kumar","98767678",address1);
		List<Grade> grades=List.of(new Grade("EA","A+"),new Grade("MPP","A"));
		student.setGrade(grades);
		student1.setGrade(List.of(new Grade("EA","A+")));
		studentRepository.save(student);
		studentRepository.save(student1);

		List<Student> students=studentRepository.findByName("Kush Raj Rimal");
		List<Student> students1=studentRepository.findByPhone("98767678");
		List<Student> students2=studentRepository.findStudentWithCity("London");

		List<Student> students3=studentRepository.findStudentsWithCourse("EA");
		List<Student> students4=studentRepository.findStudentsWithAPlusFor("EA");

		System.out.println("Named Kush Raj Rimal");
		students.forEach(System.out::println);
		System.out.println("with phone");
		students1.forEach(System.out::println);
		System.out.println("With City");
		students2.forEach(System.out::println);

		System.out.println("With Course");
		students3.forEach(System.out::println);
		System.out.println("With A+ in EA");
		students4.forEach(System.out::println);

        /*create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);

		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);

		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);

		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());

		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");

		List<Customer> customers = customerRepository.findCustomerWithCreditCardType("Visa");

		customers.forEach(System.out::println);*/


	}

}
