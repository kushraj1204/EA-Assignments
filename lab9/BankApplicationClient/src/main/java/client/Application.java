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
		restTemplate.postForLocation(serverUrl+"/deposit", new DepositRequest(1387868687, 25,CurrencyCode.USD));
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// deposit
		restTemplate.postForLocation(serverUrl+"/deposit", new DepositRequest(1387868687, 25,CurrencyCode.EURO));

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/withdraw", new WithdrawRequest(1387868687, 5,CurrencyCode.USD));

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/withdraw", new WithdrawRequest(1387868687, 5,CurrencyCode.EURO));

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1387868687);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// get account
		accountDto= restTemplate.getForObject(serverUrl+"/{accountNumber}", AccountDto.class, 1263862);
		System.out.println(accountDto);
		System.out.println(accountDto.getBalance());

		// withdraw
		restTemplate.postForLocation(serverUrl+"/transfer", new FundTransferRequest(1387868687,1263862, 5,"Just testing client"));

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
