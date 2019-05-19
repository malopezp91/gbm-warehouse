package gbm;

import com.google.inject.AbstractModule;

import gbm.dao.ProductDao;
import gbm.dao.ProductRepositoryImpl;
import gbm.service.WarehouseService;
import gbm.service.WarehouseServiceImpl;

class WarehouseModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ProductDao.class).to(ProductRepositoryImpl.class);
        bind(WarehouseService.class).to(WarehouseServiceImpl.class);
    }
}