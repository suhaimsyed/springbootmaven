FROM openjdk:8
VOLUME /tmp
ADD target/springbootdemo-0.0.1-SNAPSHOT.jar springbootdemo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","springbootdemo-0.0.1-SNAPSHOT.jar"]