package gbm;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import gbm.model.Product;
import gbm.service.WarehouseService;

/**
 * Main class form GBM warehouse app.
 */
public final class App {
    /* 
        Main method, to be called by Heroku/Spark to start up server and app.
    */
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new WarehouseModule());
        WarehouseService warehouseService = injector.getInstance(WarehouseService.class);

        get("/health", (req, res) -> "Warehouse Service is up and running!");

        get("/api/product/:id", (req, res) -> {
                String id = req.params("id");
                Product product = warehouseService.getProduct(id);
                if (product.getUuid() == null) {
                    return new Gson().toJson(null);
                } else {
                    return new Gson().toJson(product);            
                }     
            
        });

        post("/api/product", (req, res) -> {
            Product productToInsert = new Gson().fromJson(req.body(), Product.class);
            Product insertedProduct = warehouseService.createNewProduct(productToInsert);
            return new Gson().toJson(insertedProduct);
        });
        
        put("/api/product/:id", (req, res) -> {
            String id = req.params("id");
            Product productToUpdate = new Gson().fromJson(req.body(), Product.class);
            productToUpdate.setUuid(id);
            warehouseService.updateProduct(productToUpdate);
            return new Gson().toJson(productToUpdate);
        });

        delete("api/product/:id", (req, res) -> {
            String id = req.params("id");
            warehouseService.deleteProduct(id);
            return "";
        });
    }
}
