package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kush
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o.orderNumber from Order o where o.status=:status")
    List<String> findOrderNumbersWithStatus(String status);

    @Query("select o.orderNumber from Order o where o.customer.address.city=:city")
    List<String> findOrderNumbersOfCustomersFromCity(String city);
}
