FROM openjdk:17-jdk-slim
COPY target/walletPr.jar ./walletPr.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/walletPr.jar"]