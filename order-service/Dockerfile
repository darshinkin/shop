FROM openjdk:13-jdk-alpine
ARG JAR_FILE=build/libs/order-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

# Use this command to build the docker image:
#    $ docker build -t order .
#    $ docker build -t arshinkinda/order-origin .
#    $ docker tag order arshinkinda/order-origin

#   Local run
#    $ docker run --rm -it -p 8084:8084 --name order arshinkinda/order-origin:latest
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO -DAWS_REGION=us-east-1 -DAWS_SQS_QUEUE=DemoQueue" -p 8084:8084 --name order arshinkinda/order-origin:latest

#   Run on AWS
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=INFO" -p 8084:8084 --name order arshinkinda/order-origin:latest
