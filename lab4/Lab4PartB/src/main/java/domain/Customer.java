package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity

public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;

	private String lastName;

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Address address;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Collection<Order> orders = new ArrayList<>();

	public Customer() {
	}

	public Customer(String firstName, String lastName, String street,
					String city, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new Address(street, city, zip);
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(orders);
	}

	public boolean addOrder(Order order) {
		boolean added = orders.add(order);
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = orders.remove(order);
		if (removed) {
			orders.remove(order);
		}
		return removed;
	}

}
