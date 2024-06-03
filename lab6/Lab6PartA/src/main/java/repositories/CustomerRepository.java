package repositories;

import domain.Customer;
import dto.CustomerName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kush
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll();

    List<Customer> findByCountry(String country);

    @Query("select new dto.CustomerName(c.firstName,c.lastName) from Customer c where c.address.city=:city")
    List<CustomerName> findCustomerNamesByCity(String city);
}
