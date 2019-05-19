package gbm.dao;

import gbm.model.Product;

public interface ProductDao {

    Product getProduct(String id);

    Product createProduct(Product product);
    
    Product updateProduct(Product product);

    void deleteProduct(String id);
}