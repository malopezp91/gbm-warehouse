package gbm.model;

import java.util.List;

public final class Product {
    private String uuid;
    private String name;
    private String description;
    private double price;
    private long quantity;
    private List<Long> quantityHistory;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
    
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantityHistory(List<Long> quantityHistory){
        this.quantityHistory = quantityHistory;
    }

    public List<Long> getQuantityHistory() {
        return this.quantityHistory;
    }

    public static class Builder {
        private String uuid;
        private String name;
        private String description;
        private double price;
        private long quantity;
        private List<Long> quantityHistory;

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setQuantityHistory(List<Long> quantityHistory) {
            this.quantityHistory = quantityHistory;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.uuid = this.uuid;
            product.name = this.name;
            product.description = this.description;
            product.price = this.price;
            product.quantity = this.quantity;
            return product;
        }


    }
}