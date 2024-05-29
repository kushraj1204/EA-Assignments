package edu.miu.Lab2PartA;

import edu.miu.Lab2PartA.customers.CustomerService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Lab2PartAApplication {

	public static void main(String[] args) {
		ApplicationContext context = new
				AnnotationConfigApplicationContext(Lab2PartAApplication.class);
		CustomerService customerService =
				context.getBean("customerService",CustomerService.class);
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

	}

}
