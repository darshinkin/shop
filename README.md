# shop
Online shop in cloud.

### REST API

* Retrieve product by ID
> GET /v1/product/{id} 
> curl -v http://localhost:8082/v1/product/1

* Create product
> POST /v1/product
> curl -v -H "Content-Type: application/json" -X POST http://localhost:8082/v1/product -d "{\"productName\":\"car\"}"

### DOCKER

* Build image
> docker build --build-arg JAR_FILE=build/libs/\*.jar -t com.home.shop/shop-origin/develop:latest .

* Run container
> docker run --rm -it -p 8082:8082 --name shop com.home.shop/shop-origin/develop:latest

> with JAVA_OPTS: <br/>
> docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=TRACE" -p 8082:8082 --name shop com.home.shop/shop-origin/develop:latest
