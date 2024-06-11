package bank;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.dto.AccountEntryDto;
import bank.dto.CustomerDto;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.FundTransferRequest;
import bank.service.AccountService;
import bank.service.CurrencyConverterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Collection;

@EnableJms
//@EnableKafka
@SpringBootApplication
public class Application implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(Application.class);

	final AccountService accountService;

	public Application(AccountService accountService) {
		this.accountService = accountService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		accountService.createAccount(new AccountCreateRequest(1263862, "Frank Brown"));
		accountService.createAccount(new AccountCreateRequest(4253892, "John Doe"));

		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);

		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.depositEuros(4253892, 12000);
		accountService.transferFunds(new FundTransferRequest(4253892, 1263862, 100, "payment of invoice 10232"));

		// show balances
		Collection<AccountDto> accountlist = accountService.getAllAccounts();
		CustomerDto customer = null;
		for (AccountDto account : accountlist) {
			customer = account.getCustomer();
			logger.info("Statement for Account {}",account.getAccountNumber());
			logger.info("Account Holder {}",customer.getName());
			logger.info("-Date-------------------------"
					+ "-Description------------------"
					+ "-Amount-------------");
			for (AccountEntryDto entry : account.getEntryList()) {
				logger.info("Account Holder {}",customer.getName());
				logger.info("{}{}{}\n", entry.getDate()
						.toString(), entry.getDescription(), entry.getAmount());
			}
			logger.info("----------------------------------------"
					+ "----------------------------------------");
			logger.info("{}{}{}\n\n", "", "Current Balance:",
					account.getBalance());
		}
	}
}


