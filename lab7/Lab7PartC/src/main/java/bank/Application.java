package bank;


import bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	BankService bankService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			bankService.createCustomerAndAccount(12, "Jack Bauer", "jbauer@yahoo.com","1223");
		}
		catch (Exception e){
			System.out.println("Could not save account");
		}
		try {
			bankService.createCustomerAndAccount(14, "Frank Brown", "frankbrown@gmail.com","1248");
		}
		catch (Exception e){
			System.out.println("Could not save account");
		}
	}

}
