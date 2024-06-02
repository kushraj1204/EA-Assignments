package repositories;

import domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kush
 */
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

}
