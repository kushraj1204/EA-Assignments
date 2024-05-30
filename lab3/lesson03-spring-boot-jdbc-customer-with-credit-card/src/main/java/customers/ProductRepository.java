package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
        jdbcTemplate.update("DELETE from supplier",namedParameters);
    }
    public void save(Product product) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productNumber, :name, :price)",namedParameters);

        Map<String,Object> namedParametersSupplier = new HashMap<String,Object>();
        namedParameters.put("productNumber", product.getProductNumber());
        namedParameters.put("supplierNumber", product.getSupplier().getSupplierNumber());
        namedParameters.put("name", product.getSupplier().getName());
        namedParameters.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :productNumber,:supplierNumber, :name, :phone)",namedParameters);

    }

    public Product findByProductNumber(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));
        
        Supplier supplier=findSupplierByProductNumber(productNumber);
        product.setSupplier(supplier);
        return product;

    }

    private Supplier findSupplierByProductNumber(int productNumber) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getInt("supplierNumber"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }

    public Product findByProductName(String productName){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("name", productName);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "name =:name ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));

        Supplier supplier=findSupplierByProductNumber(product.getProductNumber());
        product.setSupplier(supplier);
        return product;

    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getDouble("price")));

        for(Product product:products){
            Supplier supplier = findSupplierByProductNumber(product.getProductNumber());
            product.setSupplier(supplier);
        }

        return products;
    }



    public void removeProduct(int productNumber) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        jdbcTemplate.update("DELETE FROM product WHERE productNumber = :productNumber",namedParameters);

        jdbcTemplate.update("DELETE FROM supplier WHERE productNumber = :productNumber",namedParameters);

    }




}
