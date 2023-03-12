FROM openjdk:17
EXPOSE 8080
ADD target/consumer-statement-processor.jar consumer-statement-processor.jar
ENTRYPOINT ["java","-jar","/consumer-statement-processor.jar"]