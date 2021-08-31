FROM openjdk:13-jdk-alpine
ARG JAR_FILE=build/libs/product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

# Use this command to build the docker image:
#    $ docker build -t product .
#    $ docker tag product com.shop.product/product-origin/develop:latest
# for run
#    $ docker run --rm -it -p 8082:8082 --name product com.shop.product/product-origin/develop:latest
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=TRACE" -p 8082:8082 --name product com.shop.product/product-origin/develop:latest
