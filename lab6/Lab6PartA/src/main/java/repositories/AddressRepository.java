package repositories;

import domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kush
 */
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query(value = "select * from address a where a.city=:city",nativeQuery = true)
    List<Address> findAllByCity(String city);
}
