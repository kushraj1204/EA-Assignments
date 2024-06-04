package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TraceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private TraceRecordService traceRecordService;


	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		String traceText="";
		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			String emailText="Welcome "+customerName;
			traceText="Customer "+ customerName + " created with account "+AccountNumber;
			emailSender.sendEmail(emailAddress, emailText);
		}
		catch (RuntimeException re){
			traceText="Could not create customer "+customerName+" with account "+AccountNumber;
			emailSender.sendEmail(emailAddress, "We could not create your account "+AccountNumber);
			throw re;
		}
		finally {
			traceRecordService.createTraceRecord(traceText);
		}
	}

}
