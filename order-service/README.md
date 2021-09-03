# order-service
Order service for online shop in cloud.

### REST API

* Create order. It takes the shopping cart content as an argument.
> POST /v1/order/checkout <br/>
> *Example* <br/>
> curl  -v -H "Content-Type: application/json" -X POST http://localhost:8084/v1/order -d "{ \"cartId\": 1, \"products\": [
{
\"productId\": 2,
\"productName\": \"TV-set\"
},
{
\"productId\": 5,
\"productName\": \"orange\"
},
{
\"productId\": 4,
\"productName\": \"pear\"
},
{
\"productId\": 3,
\"productName\": \"apple\"}]}"

### DOCKER

* Build image
> docker build -t arshinkinda/order-origin .

* Run container
> docker run --rm -it -d -p 8084:8084 --name order arshinkinda/order-origin:latest

> with JAVA_OPTS: <br/>
> docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO" -p 8084:8084 --name order arshinkinda/order-origin:latest
