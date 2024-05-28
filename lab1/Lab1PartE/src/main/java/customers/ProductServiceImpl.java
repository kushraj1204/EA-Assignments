package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kush
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public void addProduct(String name, String category, double price, String description,
                           String email) {
        Product product=new Product(name,category,price,description);
        productRepository.save(product);
        emailSender.sendEmail(email, "Product  " + name + " has arrived finally. Grab it before the stock is over.");
    }
}
