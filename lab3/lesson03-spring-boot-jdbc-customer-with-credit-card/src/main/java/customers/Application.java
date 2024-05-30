package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*customerRepository.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		System.out.println(customerRepository.getCustomer(101));
		System.out.println(customerRepository.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.getAllCustomers());*/
		productRepository.clearDB();
		Product product=new Product(1,"Mouse",78.00,new Supplier(1,"Supplier1","987-987-765"));
		Product product1=new Product(2,"Keyboard",788.00,new Supplier(2,"Supplier2","987-987-766"));
		productRepository.save(product);
		productRepository.save(product1);

		Product firstProduct=productRepository.findByProductNumber(1);
		System.out.println(firstProduct);

		Product secondProduct=productRepository.findByProductName("Keyboard");
		System.out.println(secondProduct);

		productRepository.getAllProducts().forEach(System.out::println);

		productRepository.removeProduct(1);

		productRepository.getAllProducts().forEach(System.out::println);

	}

}
