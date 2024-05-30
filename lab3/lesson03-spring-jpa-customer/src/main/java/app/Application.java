package app;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*		customerRepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerRepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerRepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerRepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerRepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerRepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();*/


//		- Create 3 books, and save them in the database
//				- Retrieve all books from the database and display them in the console
//				- Update a book
//		- Delete a book (not the book that was just updated)
//		- Retrieve all books from the database again and display them in the console

		bookRepository.save(new Book("A Song of Ice and Fire","2121-434-432","George RR Martin",500));
		bookRepository.save(new Book("The Silmarillion","2121-434-436","J.R.R Tolkien",600));
		bookRepository.save(new Book("Dune: Prophecy","2121-434-438","Frank Herbert",700));

		List<Book> books=bookRepository.findAll();
		books.forEach(System.out::println);

		Optional<Book> gameOfThronesOpt=bookRepository.findById(1L);
		if(gameOfThronesOpt.isPresent()){

		Book gameOfThrones=gameOfThronesOpt.get();
		gameOfThrones.setPrice(650.00);
		bookRepository.save(gameOfThrones);
		}

		Optional<Book> lotrOpt=bookRepository.findById(2L);
		if(lotrOpt.isPresent()){
			Book lotr=lotrOpt.get();
			bookRepository.delete(lotr);
		}

		List<Book> updatedBooks=bookRepository.findAll();
		updatedBooks.forEach(System.out::println);





	}

}
