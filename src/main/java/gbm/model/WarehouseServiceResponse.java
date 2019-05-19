package gbm.model;

public class WarehouseServiceResponse {
    private int status;
    private String message;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static class Builder{
        private int status;
        private String message;

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public WarehouseServiceResponse build() {
            WarehouseServiceResponse response = new WarehouseServiceResponse();
            response.setStatus(this.status);
            response.setMessage(this.message);
            return response;

        }
    }

}