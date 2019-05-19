package gbm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gbm.model.Product;

public final class ProductOnMemoryRepositoryImpl implements ProductDao {
    private static List<Product> allProducts = new ArrayList<Product>();


    @Override
    public Product getProduct(String id) {
        return allProducts.stream().filter(product -> product.getUuid().equals(id)).findFirst().orElse(new Product());
    }

    @Override
    public Product createProduct(Product product) {
        UUID uuid = UUID.randomUUID();
        product.setUuid(uuid.toString());
        product.setQuantityHistory(new ArrayList<Long>());
        allProducts.add(product);
        return product;
    }

    @Override
    public boolean updateProduct(Product productToUpdate) {
        boolean wasRemoved = allProducts.removeIf(product -> product.getUuid().equals(productToUpdate.getUuid()));
        if (wasRemoved) {
            allProducts.add(productToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(String id) {
        return allProducts.removeIf(product -> product.getUuid().equals(id));
    }

}