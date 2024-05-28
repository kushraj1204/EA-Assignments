package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import products.ProductService;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"springconfig.xml");

		CustomerService customerService = context.getBean("customerService",
				CustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

		ProductService productService = context.getBean("productService",
				ProductService.class);
		productService.addProduct("Macbook 13 Pro","Laptops",4500,"Basically a fancy expensive laptop to brag about. Maybe a little more",
				"customers@productbazaar.com");


	}
}

