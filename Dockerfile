FROM openjdk:11
WORKDIR /usr/src/myapp
COPY ./target/urlservice-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "urlservice-0.0.1-SNAPSHOT.jar"]
