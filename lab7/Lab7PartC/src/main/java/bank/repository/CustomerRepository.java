package bank.repository;


import bank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    default void saveCustomer(Customer customer) {
//        throw new RuntimeException("could not save customer");
        save(customer);
    }

}




