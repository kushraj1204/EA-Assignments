package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/books";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// get Dune:Prophecy
		Book book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "123-456-890");
		System.out.println(book);

		// add Mahabharata
		restTemplate.postForLocation(serverUrl, new Book("676-545-434","Vyasa", "Mahabharat", 130.00));

		// get Mahabharata
		book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "676-545-434");
		System.out.println(book);

		// delete Dune:Prophecy
		restTemplate.delete(serverUrl+"/{isbn}", "123-456-890");

		// update Mahabharata
		book.setPrice(180.00);
		restTemplate.put(serverUrl+"/{isbn}" , book, "676-545-434");

		// get Mahabharata
		book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "676-545-434");
		System.out.println(book);

        // get all books
		Books books = restTemplate.getForObject(serverUrl, Books.class);
		System.out.println(books);

		// search all books with author vyasa
		String author="vyasa";
		Books booksAuthoredBy = restTemplate.getForObject(serverUrl+"/search?author="+author, Books.class);
		System.out.println(booksAuthoredBy);
	}

}
