FROM openjdk

COPY . /workspace

WORKDIR /workspace

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "target/Project1-1.0-SNAPSHOT-jar-with-dependencies.jar"]