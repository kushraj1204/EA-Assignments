package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/accounts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// add Account
		restTemplate.postForLocation(serverUrl, new AccountCreateRequest(1387868687, "Kush Raj Rimal"));

		// get account
		AccountDto accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// deposit
		restTemplate.postForLocation(serverUrl+"/{accountNumber}/deposit", new DepositRequest(1387868687, 25,CurrencyCode.USD),1387868687);
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// deposit
		restTemplate.postForLocation(serverUrl+"/{accountNumber}/deposit", new DepositRequest(1387868687, 25,CurrencyCode.EURO),1387868687);

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/{accountNumber}/withdraw", new WithdrawRequest( 5,CurrencyCode.USD),1387868687);

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/{accountNumber}/withdraw", new WithdrawRequest( 5,CurrencyCode.EURO),1387868687);

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1263862);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/{accountNumber}/transfer", new FundTransferRequest(1263862, 5,"Just testing client"),1387868687);

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1263862);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());


	}

}
