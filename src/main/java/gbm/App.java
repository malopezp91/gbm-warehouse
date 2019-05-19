package gbm;

import static spark.Spark.*;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import gbm.model.Product;
import gbm.model.WarehouseServiceResponse;
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
        Gson gsonParser = new Gson();
        WarehouseService warehouseService = injector.getInstance(WarehouseService.class);

        get("/health", (req, res) -> "Warehouse Service is up and running!");

        get("/api/product/:id", (req, res) -> {
                String id = req.params("id");
                Product product = warehouseService.getProduct(id);
                if (product.getUuid() == null) {
                    return gsonParser.toJson(buildResponse(404));
                } else {
                    return gsonParser.toJson(buildResponse(200, product));
                }     
            
        });

        post("/api/product", (req, res) -> {
            Product productToInsert = new Gson().fromJson(req.body(), Product.class);
            Product insertedProduct = warehouseService.createNewProduct(productToInsert);
            return gsonParser.toJson(buildResponse(200, insertedProduct));
        });
        
        put("/api/product/:id", (req, res) -> {
            String id = req.params("id");
            Product productToUpdate = new Gson().fromJson(req.body(), Product.class);
            productToUpdate.setUuid(id);
            boolean updateStatus = warehouseService.updateProduct(productToUpdate);
            if(updateStatus) {
                return gsonParser.toJson(buildResponse(200, productToUpdate));
            } else {
                return gsonParser.toJson(buildResponse(404));
            }
        });

        delete("api/product/:id", (req, res) -> {
            String id = req.params("id");
            boolean deleteStatus = warehouseService.deleteProduct(id);
            if (deleteStatus) {
                return gsonParser.toJson(buildResponse(200));
            } else {
                return gsonParser.toJson(buildResponse(404));
            }
            
        });
    }

    private static WarehouseServiceResponse buildResponse(int status, Product product) {
        WarehouseServiceResponse.Builder responseBuilder = new WarehouseServiceResponse.Builder();
        String productAsString = new Gson().toJson(product);
        return responseBuilder.setStatus(status).setMessage(productAsString).build();
    }

    private static WarehouseServiceResponse buildResponse(int status) {
        WarehouseServiceResponse.Builder responseBuilder = new WarehouseServiceResponse.Builder();
        return responseBuilder.setStatus(status).build();
    }
}
