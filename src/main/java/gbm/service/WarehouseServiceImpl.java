package gbm.service;

import java.util.List;

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
	public boolean updateProduct(Product product) {
		Product productInDb = productDao.getProduct(product.getUuid());

		if(productInDb != null) {
			List<Long> quantityHistory = productInDb.getQuantityHistory();
			quantityHistory.add(product.getQuantity());
			product.setQuantityHistory(quantityHistory);
			return productDao.updateProduct(product);		
		} else {
			return false;
		}

	}

	@Override
	public boolean deleteProduct(String id) {
		return productDao.deleteProduct(id);
	}

}