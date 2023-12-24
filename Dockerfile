FROM maven:3.8.6-eclipse-temurin-17-alpine AS maven
RUN apk update && apk add git
WORKDIR /app
RUN git clone https://github.com/Konstantin-Kubrak/task_3.git
WORKDIR /app/task_3
RUN mvn clean install spring-boot:repackage
EXPOSE 8080 8780
ENTRYPOINT ["java", "-javaagent:/app/task_3/jolokia.jar=port=8780,host=0.0.0.0", "-jar", "/app/task_3/target/task_3-1.jar"]
