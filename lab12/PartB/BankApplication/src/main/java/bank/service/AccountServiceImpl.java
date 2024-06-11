package bank.service;

import bank.domain.Account;
import bank.domain.CurrencyCode;
import bank.domain.Customer;
import bank.dto.AccountDto;
import bank.dto.event.AddAccountEvent;
import bank.dto.event.DepositEvent;
import bank.dto.event.TransferEvent;
import bank.dto.event.WithdrawEvent;
import bank.dto.request.AccountCreateRequest;
import bank.dto.request.DepositRequest;
import bank.dto.request.FundTransferRequest;
import bank.dto.request.WithdrawRequest;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.mapper.AccountMapper;
import bank.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
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
	private ApplicationEventPublisher publisher;

	public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger, ApplicationEventPublisher publisher) {
		this.accountRepository = accountRepository;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
        this.publisher = publisher;
    }
	@Override
	public AccountDto createAccount(AccountCreateRequest request) {
		long accountNumber= request.getAccountNumber();
		String customerName= request.getCustomerName();
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		publisher.publishEvent(new AddAccountEvent("Account added",account));
		return AccountMapper.accountToAccountDto(account);
	}
	@Override
	public AccountDto getAccount(long accountNumber) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			return AccountMapper.accountToAccountDto(account);
		}
		return null;
	}
	@Override
	public Collection<AccountDto> getAllAccounts() {
		return AccountMapper.accountToAccountDto(accountRepository.findAll());
	}

	@Override
	public void depositMoney(DepositRequest request) {
		if(request.getCurrencyCode().equals(CurrencyCode.USD)){
			deposit(request.getAccountNumber(),request.getAmount());
		} else if (request.getCurrencyCode().equals(CurrencyCode.EURO)) {
			depositEuros(request.getAccountNumber(),request.getAmount());
		}
		else{
			logger.log("Invalid currency Code");
		}
	}
	@Override
	public void deposit(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()){
			Account account=accountOpt.get();
			account.deposit(amount);
			accountRepository.save(account);
			logger.log("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
			publisher.publishEvent(new DepositEvent("Deposited USD",account));
			if (amount > 10000){
				jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
			}
		}

	}
	@Override
	public void depositEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.deposit(amountDollars);
			accountRepository.save(account);
			logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
			publisher.publishEvent(new DepositEvent("Deposited Euros",account));
			if (amountDollars > 10000) {
				jmsSender.sendJMSMessage("Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
			}
		}
	}

	@Override
	public void withdrawMoney(WithdrawRequest request) {
		if(request.getCurrencyCode().equals(CurrencyCode.USD)){
			withdraw(request.getAccountNumber(),request.getAmount());
		} else if (request.getCurrencyCode().equals(CurrencyCode.EURO)) {
			withdrawEuros(request.getAccountNumber(),request.getAmount());
		}
		else{
			logger.log("Invalid currency Code");
		}
	}
	@Override
	public void withdraw(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			account.withdraw(amount);
			accountRepository.save(account);
			publisher.publishEvent(new WithdrawEvent("Withdrawn USD",account));
			logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		}
	}
	@Override
	public void withdrawEuros(long accountNumber, double amount) {
		Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountOpt.isPresent()) {
			Account account = accountOpt.get();
			double amountDollars = currencyConverter.euroToDollars(amount);
			account.withdraw(amountDollars);
			accountRepository.save(account);
			publisher.publishEvent(new WithdrawEvent("Withdrawn Euro",account));
			logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
		}
	}
	@Override
	public void transferFunds(FundTransferRequest request) {
		long fromAccountNumber= request.getFromAccountNumber();
		long toAccountNumber= request.getToAccountNumber();
		double amount= request.getAmount();
		String description= request.getDescription();
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
			publisher.publishEvent(new TransferEvent("Transferring balance from account ",fromAccount));
		}
	}

}
