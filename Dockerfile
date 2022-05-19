FROM adoptopenjdk/openjdk11:ubi
ADD target/dzm-api-docker.jar dzm-api-docker.jar
#COPY ${JAR_FILE} dzm-api-docker.jar

ENTRYPOINT ["java","-jar","/dzm-api-docker.jar"]
EXPOSE 8081