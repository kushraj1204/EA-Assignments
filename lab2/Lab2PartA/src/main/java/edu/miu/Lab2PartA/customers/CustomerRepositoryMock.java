package edu.miu.Lab2PartA.customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author kush
 */
@Service("customerRepository")
@Profile("test")
public class CustomerRepositoryMock implements CustomerRepository {
    private Logger logger;

    public CustomerRepositoryMock(Logger logger) {
        this.logger = logger;
    }

    public void save(Customer customer) {
        // simple sleep
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CustomerRepositoryMock: saving customer "+customer.getName());
        logger.log("Customer is saved in the test DB: "+ customer.getName() );
    }

}
