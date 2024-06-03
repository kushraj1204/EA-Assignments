package app;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StopWatch;
import repository.BookRepository;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addBooks();
		changeLocationCode();
		removeOldBooks();
	}

	public void addBooks(){
		for (int x=0; x< 10000; x++){
			int year = 1900 + new Random().nextInt(100-1) + 1;;
			bookRepository.save( new Book("Harry Potter"+x, "J.K. Rowling"+x, "AA-345-"+x, year));
		}
	}

	private void changeLocationCode() {
		StopWatch sw = new StopWatch();
		sw.start();

		List<Book> bookList = bookRepository.findAll();
		for (Book book : bookList){
			String locationCode = book.getLocationCode();
			book.setLocationCode("BB"+locationCode);
			bookRepository.save(book);
		}

		sw.stop();
		long totaltime=sw.getTotalTimeMillis();
		System.out.println("Changing the location code of all books took "+totaltime+" ms");
	}

	private void removeOldBooks() {
		StopWatch sw = new StopWatch();
		sw.start();

		List<Book> bookList = bookRepository.findAll();
		for (Book book : bookList){
			if (book.getPublicationYear() < 1950)
			   bookRepository.delete(book);
		}

		sw.stop();
		long totaltime=sw.getTotalTimeMillis();
		System.out.println("Removing old books took "+totaltime+" ms");
	}

}

