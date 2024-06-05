package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private AccountRepository accountRepository;
	private CurrencyConverter currencyConverter;
	private JMSSender jmsSender;
	private Logger logger;

	public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
		this.accountRepository = accountRepository;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
	}

	public AccountDto createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return AccountMapper.accountToAccountDto(account);
	}

	public void deposit(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()){
			Account account=accountOpt.get();
			account.deposit(amount);
			accountRepository.save(account);
			logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
			if (amount > 10000){
				jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
			}
		}
	}

	public AccountDto getAccount(long accountNumber) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			return AccountMapper.accountToAccountDto(account);
		}
		return null;
	}

	public Collection<AccountDto> getAllAccounts() {
		return AccountMapper.accountToAccountDto(accountRepository.findAll());
	}

	public void withdraw(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			account.withdraw(amount);
			accountRepository.save(account);
			logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		}
	}

	public void depositEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.deposit(amountDollars);
			accountRepository.save(account);
			logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
			if (amountDollars > 10000) {
				jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
			}
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.withdraw(amountDollars);
			accountRepository.save(account);
			logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		}
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Optional<Account> fromAccountOpt = accountRepository.findByAccountNumber(fromAccountNumber);
		Optional<Account> toAccountOpt = accountRepository.findByAccountNumber(toAccountNumber);
		if(fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
			Account fromAccount = fromAccountOpt.get();
			Account toAccount = toAccountOpt.get();
			fromAccount.transferFunds(toAccount, amount, description);
			accountRepository.save(fromAccount);
			accountRepository.save(toAccount);
			logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
			if (amount > 10000) {
				jmsSender.sendJMSMessage("TransferFunds of $ " + amount + " from account with accountNumber= " + fromAccount + " to account with accountNumber= " + toAccount);
			}
		}
	}

}
