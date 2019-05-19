package gbm.service;

import gbm.model.Product;

public interface WarehouseService {
    Product getProduct(String id);

    Product createNewProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(String id);
}