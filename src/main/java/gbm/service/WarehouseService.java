package gbm.service;

import gbm.model.Product;

public interface WarehouseService {
    Product getProduct(String id);

    Product createNewProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(String id);
}