# cart-service
Cart service for online shop in cloud.

### REST API

* Retrieve cart by ID. If no cart – create a new one and store in DB.
> GET /v1/cart/{id} <br/>
> *Example:* <br/>
> curl -v http://localhost:8083/v1/cart/1

* Delete cart.
> DELETE /v1/cart/{id} <br/>
> *Example:* <br/>
> curl -X DELETE http://localhost:8083/v1/cart/1

* Put a default product to the cart.
> POST /v1/cart/populate/{id} <br/>
> *Example* <br/>
> curl  -v -H "Content-Type: application/json" -X POST http://localhost:8083/v1/cart/1

### DOCKER

* Build image
> docker build -t arshinkinda/cart-origin .

* Run container
> docker run --rm -it -d -p 8083:8083 --name cart arshinkinda/cart-origin:latest

> with JAVA_OPTS: <br/>
> docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO" -p 8083:8083 --name cart arshinkinda/cart-origin:latest
