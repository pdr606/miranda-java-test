FROM openjdk

WORKDIR /app

COPY target/java-test.jar /app/java-test.jar

ENTRYPOINT ["java", "-jar", "java-test.jar"]