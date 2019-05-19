package gbm.service;

import com.google.inject.Inject;

import gbm.model.Product;
import gbm.dao.ProductDao;

public final class WarehouseServiceImpl implements WarehouseService {
    ProductDao productDao;

    @Inject
    WarehouseServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

	@Override
	public Product getProduct(String id) {
        return productDao.getProduct(id);
    }
    
    @Override
	public Product createNewProduct(Product product) {
		return productDao.createProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(String id) {
		productDao.deleteProduct(id);
	}

}