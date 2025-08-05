FROM openjdk:21
ADD https://storage.googleapis.com/emerald-spring-465406-s1-s2/spring-petclinic-3.5.0-SNAPSHOT.jar /spring-petclinic.jar
EXPOSE 8080
CMD ["java","-jar", "spring-petclinic.jar"]