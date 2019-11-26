# Centos based container with Java
#FROM eim/java8:latest

# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

VOLUME /tmp
VOLUME /log

#ARG JAR_FILE
#COPY ./target/[artifactId]-SNAPSHOT.jar [artifactId].jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/[artifactId].jar"]



# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
#ADD ${JAR_FILE} /usr/share/eim-petclinic-0.0.1-SNAPSHOT.jar
ADD ./target/eim-petclinic-0.0.1-SNAPSHOT.jar /usr/share/eim-petclinic-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/eim-petclinic-0.0.1-SNAPSHOT.jar"]
