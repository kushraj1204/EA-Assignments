package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kush
 */
@Configuration
public class AppConfig {
    @Bean
    public CustomerService customerService(){
        CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl();
        customerServiceImpl.setCustomerRepository(customerRepository());
        customerServiceImpl.setEmailSender(emailSender());
        CustomerService customerService=customerServiceImpl;
        return customerService;
    }

    @Bean
    public CustomerRepository customerRepository(){
        CustomerRepository customerRepository=new CustomerRepositoryImpl(logger());
        return customerRepository;
    }

    @Bean
    public Logger logger(){
        Logger logger=new LoggerImpl();
        return logger;
    }

    @Bean
    public EmailSender emailSender(){
        EmailSender emailSender=new EmailSenderImpl(logger());
        return emailSender;
    }
}
