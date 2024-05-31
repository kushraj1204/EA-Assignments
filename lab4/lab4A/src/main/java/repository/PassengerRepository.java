package repository;

import domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
