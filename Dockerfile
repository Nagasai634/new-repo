FROM openjdk:21
WORKDIR /app
ADD https://storage.googleapis.com/emerald-spring-465406-s1-s2/spring-petclinic-3.5.0-SNAPSHOT.jar /app/spring-petclinic.jar
EXPOSE 8080
CMD ["java","-jar", "/app/spring-petclinic.jar"]