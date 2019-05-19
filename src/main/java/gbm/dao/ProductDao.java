package gbm.dao;

import gbm.model.Product;

public interface ProductDao {

    Product getProduct(String id);

    Product createProduct(Product product);
    
    boolean updateProduct(Product product);

    boolean deleteProduct(String id);
}