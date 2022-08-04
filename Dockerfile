FROM adoptopenjdk/openjdk11:ubi
ENV TZ="Asia/Kolkata"
COPY ./target/spring-boot-jwt-0.0.1-SNAPSHOT.jar /c.jar
CMD ["java","-jar","/c.jar"]
