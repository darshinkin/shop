FROM openjdk:13-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

# Use this command to build the docker image:
#    $ docker build --build-arg JAR_FILE=build/libs/\*.jar -t com.home.shop/shop-origin/develop:latest .
# for run
#    $ docker run --rm -it -p 8082:8082 --name shop com.home.shop/shop-origin/develop:latest
#    $ docker run --rm -it -e JAVA_OPTS="-Dlogging.level.root=TRACE" -p 8082:8082 --name shop com.home.shop/shop-origin/develop:latest
