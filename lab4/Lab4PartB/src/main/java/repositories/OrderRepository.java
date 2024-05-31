package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
