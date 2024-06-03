package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kush
 */
public interface CDRepository extends JpaRepository<CD,Long> {

    List<CD> findAllByArtistIgnoreCaseAndPriceLessThan(String artist,double price);

    List<CD> findByArtist(String artist);

    @Query("select cd from CD cd where cd.artist=:artist and cd.price > :price")
    List<CD> findAllByArtistIgnoreCaseAndPriceGreaterThan(String artist,double price);

    @Query(value = "select * from `cd` cd inner join `product` pd on cd.id=pd.id where cd.artist=:artist",nativeQuery = true)
    List<CD> findByArtistName(String artist);
}
