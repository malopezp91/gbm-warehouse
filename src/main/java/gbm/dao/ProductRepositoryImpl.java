package gbm.dao;

import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import gbm.model.Product;

public final class ProductRepositoryImpl implements ProductDao {

    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    DB database = mongoClient.getDB("gbm_warehouse");
    DBCollection collection = database.getCollection("Products");

    @Override
    public Product getProduct(String id) {        
        DBObject query = new BasicDBObject("_id", id);
        DBCursor cursor = collection.find(query);
        DBObject product = cursor.one();


        if(product == null) {
            return new Product();
        } else {
            return new Product.Builder()
                .setUuid(product.get("_id").toString())
                .setName(product.get("name").toString())
                .setDescription(product.get("description").toString())
                .setPrice(Double.parseDouble(product.get("price").toString()))
                .setQuantity(Long.parseLong(product.get("quantity").toString()))
                .build() ;
        }
    }

    @Override
    public Product createProduct(Product product) {
        UUID uuid = UUID.randomUUID();
            
        DBObject productToBeInserted = new BasicDBObject().append("_id", uuid.toString())
        .append("name", product.getName())
        .append("description", product.getDescription())
        .append("price", product.getPrice())
        .append("quantity", product.getQuantity());

        collection.insert(productToBeInserted);

        product.setUuid(uuid.toString());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        DBObject query = new BasicDBObject("_id", product.getUuid());
        DBObject productToBeUpdated = new BasicDBObject().append("_id", product.getUuid())
        .append("name", product.getName())
        .append("description", product.getDescription())
        .append("price", product.getPrice())
        .append("quantity", product.getQuantity());

        System.out.println(productToBeUpdated);

        collection.update(query, productToBeUpdated);

        return product;
    }

    @Override
    public void deleteProduct(String id) {
        collection.remove(new BasicDBObject("_id", id));
    }

}