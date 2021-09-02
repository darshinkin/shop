FROM openjdk:13-jdk-alpine
ARG JAR_FILE=build/libs/cart-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

# Use this command to build the docker image:
#    $ docker build -t cart .
#    $ docker build -t arshinkinda/cart-origin .
#    $ docker tag cart arshinkinda/cart-origin

#   Local run
#    $ docker run --rm -it -p 8083:8083 --name cart com.shop.cart/cart-origin/develop:latest
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=TRACE" -p 8083:8083 --name cart com.shop.cart/cart-origin/develop:latest
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO -DDB_URI=jdbc:postgresql://192.168.0.1:5432/shop_cart" -p 8083:8083 -p 5432:5432 --name cart arshinkinda/cart-origin

#   Run on AWS
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO -DDB_URI=jdbc:postgresql://cart.c5qz0zmver3m.us-east-1.rds.amazonaws.com:5432/cart -DDB_USERNAME=postgres -DDB_PASSWORD=postgres" -p 8083:8083 -p 5432:5432 --name cart arshinkinda/cart-origin

#   Create a custom bridge Docker network:
#   $ docker network create -d bridge --subnet 192.168.0.0/24 --gateway 192.168.0.1 mynet
#   Confirm it works again, but we’ll use the new network:
# Start the Alpine container, but this time we'll use our custom network.
#   $ docker container run --rm -it --net=mynet alpine sh
# Install the ping utility.
#   $ apk update && apk add iputils
# Ping the custom IP address we set up.
#   $ ping 192.168.0.1
