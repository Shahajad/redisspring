FROM openjdk:13.0.2

COPY ./target/redisspring-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch redisspring-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","redisspring-0.0.1-SNAPSHOT.jar"]
