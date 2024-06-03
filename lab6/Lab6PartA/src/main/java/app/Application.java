package app;

import domain.*;
import dto.CustomerName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CDRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	final
	OrderRepository orderRepository;

	final
	CustomerRepository customerRepository;

	final
	CDRepository cdRepository;

	final AddressRepository addressRepository;

	public Application(OrderRepository orderRepository, CustomerRepository customerRepository, CDRepository cdRepository, AddressRepository addressRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.cdRepository = cdRepository;
        this.addressRepository = addressRepository;
    }


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Book product = new Book();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		product.setIsbn("34567-8765");
		OrderLine ol1 = new OrderLine(2, product);

		CD product2 = new CD();
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(9);
		product2.setArtist("U2");
		OrderLine ol2 = new OrderLine(4, product2);

		DVD product3 = new DVD();
		product3.setName("Star Wars");
		product3.setDescription("Movie");
		product3.setPrice(12.98);
		product3.setGenre("Sci-Fi");
		OrderLine ol3 = new OrderLine(4, product3);

		Order o1 = new Order("234743", "12/10/06", "closed");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"Amsterdam", "43221","USA");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);


		/*List<Order> orders=orderRepository.findAll();
		for (Order order:orders){
			printOrder(order);
		}*/
		List<Customer> customers=customerRepository.findAll();
		System.out.println("All customers");
		customers.forEach(System.out::println);
		List<CD> cds=cdRepository.findAllByArtistIgnoreCaseAndPriceLessThan("U2",10);
		System.out.println("All CDs by artist and amount less than");
		cds.forEach(System.out::println);

		List<Customer> customerFromUSA=customerRepository.findByCountry("USA");
		System.out.println("All customersFromUSA");
		customerFromUSA.forEach(System.out::println);

		List<CD> cdsByArtist=cdRepository.findByArtist("U2");
		System.out.println("All cdsByArtist");
		cdsByArtist.forEach(System.out::println);

		System.out.println("Order Nums By status closed");
		List<String> orderNums=orderRepository.findOrderNumbersWithStatus("closed");
		orderNums.forEach(System.out::println);

		System.out.println("Customer names By City amsterdam");
		List<CustomerName> customerNames=customerRepository.findCustomerNamesByCity("Amsterdam");
		customerNames.forEach(System.out::println);

		System.out.println("Order Numbers of Customers from City");
		List<String> orderNosOfCustomersFromCity=orderRepository.findOrderNumbersOfCustomersFromCity("Amsterdam");
		orderNosOfCustomersFromCity.forEach(System.out::println);

		System.out.println("CDs by artist name and price greater than");
		List<CD> cdsByArtistAndPriceGreaterThan=cdRepository.findAllByArtistIgnoreCaseAndPriceGreaterThan("U2",6);
		cdsByArtistAndPriceGreaterThan.forEach(System.out::println);

		System.out.println("All addresses where city is");
		List<Address> addresses=addressRepository.findAllByCity("Amsterdam");
		addresses.forEach(System.out::println);

		System.out.println("All cds by artist name");
		List<CD> cdList=cdRepository.findByArtistName("U2");
		cdList.forEach(System.out::println);







	}

	public static void printOrder(Order order) {
		System.out.println("\\n\\n\\n\\n");
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}
		System.out.println("\\n\\n\\n\\n");
	}
}
