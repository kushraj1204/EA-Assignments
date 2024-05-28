package products;

/**
 * @author kush
 */
public interface ProductService {
    void addProduct(String name, String category, double price, String description,
                    String email);
}
