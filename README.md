# shop
Online shop in cloud.

### REST API

* Retrieve product by ID
> GET /v1/product/{id} 
> curl -v http://localhost:8082/v1/product/1

* Create product
> POST /v1/product
> curl -v -H "Content-Type: application/json" -X POST http://localhost:8082/v1/product -d "{\"productName\":\"car\"}"
