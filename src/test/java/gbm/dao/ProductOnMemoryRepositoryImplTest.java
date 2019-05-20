package gbm.dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import gbm.model.Product;

public final class ProductOnMemoryRepositoryImplTest {

    @Test
    public void insertProduct_happyPath(){
        Product product = new Product.Builder().setName("Product 1").setPrice(5.6).setDescription("This is a Product").setQuantity(9).build();
        ProductDao productDao = new ProductOnMemoryRepositoryImpl();

        Product insertedProduct = productDao.createProduct(product);

        Assert.assertNotNull(insertedProduct.getUuid());
        Assert.assertEquals(product.getName(), insertedProduct.getName());
        Assert.assertEquals(product.getDescription(), insertedProduct.getDescription());
        Assert.assertEquals(product.getQuantity(), insertedProduct.getQuantity());
        Assert.assertEquals(product.getPrice(), insertedProduct.getPrice(), 0.0);
        Assert.assertEquals(product.getQuantityHistory(), new ArrayList<Long>());
    }

    @Test
    public void getProduct_happyPath() {
        Product product = new Product.Builder().setName("Product 1").setPrice(5.6).setDescription("This is a Product").setQuantity(9).build();
        ProductDao productDao = new ProductOnMemoryRepositoryImpl();
        Product insertedProduct = productDao.createProduct(product);

        Product retrievedProduct = productDao.getProduct(insertedProduct.getUuid());

        Assert.assertNotNull(retrievedProduct.getUuid());
        Assert.assertEquals(insertedProduct.getUuid(), retrievedProduct.getUuid());
    }

    @Test
    public void updateProduct_happyPath(){
        Product product = new Product.Builder().setName("Product 1").setPrice(5.6).setDescription("This is a Product").setQuantity(9).build();
        ProductDao productDao = new ProductOnMemoryRepositoryImpl();
        Product insertedProduct = productDao.createProduct(product);
        Product productToUpdate = new Product.Builder().setUuid(insertedProduct.getUuid()).setName("New Product Name").setDescription("New Description").setPrice(10.5).setQuantity(15).build();

        boolean updateStatus = productDao.updateProduct(productToUpdate);
        Product updatedProduct = productDao.getProduct(productToUpdate.getUuid());

        Assert.assertTrue(updateStatus);
        Assert.assertEquals(productToUpdate.getName(), updatedProduct.getName());
        Assert.assertEquals(productToUpdate.getDescription(), updatedProduct.getDescription());
        Assert.assertEquals(productToUpdate.getPrice(), updatedProduct.getPrice(), 0.0);
        Assert.assertEquals(productToUpdate.getQuantity(), updatedProduct.getQuantity());
    }


    @Test
    public void deleteProduct_happyPath(){
        Product product = new Product.Builder().setName("Product 1").setPrice(5.6).setDescription("This is a Product").setQuantity(9).build();
        ProductDao productDao = new ProductOnMemoryRepositoryImpl();
        Product insertedProduct = productDao.createProduct(product);

        boolean deleteStatus = productDao.deleteProduct(insertedProduct.getUuid());
        Product deletedProduct = productDao.getProduct(insertedProduct.getUuid());

        Assert.assertTrue(deleteStatus);
        Assert.assertNull(deletedProduct.getUuid());
    }
}