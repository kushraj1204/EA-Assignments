package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kush
 */
@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    @OrderColumn(name="sequence")
    private List<Flight> flights=new ArrayList<>();

    public Passenger() {
    }

    public Passenger(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }
}
