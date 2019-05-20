# Product Warehouse Service
This application is built using the Spark Framework.
It has 4 REST endpoints, that allow 4 CRUD operations.

## Tech stack
* Spark framework as web server.
* Guice for dependency injection.
* Current Dao Implementation stores Products on memory.

## Run tests
`mvn clean test`

## Create jar executable
`mvn clean package`

## Execute jar/application in default port 4567
`java -jar target/warehouse-1.0-SNAPSHOT-shaded.jar`

## Health endpoint
`localhost:4567/health`

## Create product endpoint(POST)
`localhost:4567/api/product`
```json
Body:
{
  "name": "Desk",
  "description": "A new Desk",
  "price": 85.0,
  "quantity": 9
} 
```

## Get product endpoint(GET)
`localhost:4567/api/product/:id`

## Create product endpoint(POST)
`localhost:4567/api/product/:id`
```json
Body:
{
  "name": "Updated Desk",
  "description": "A new Desk",
  "price": 70.0,
  "quantity": 15
} 
```

## Delete product endpoint(DELETE)
`localhost:4567/api/product/:id`