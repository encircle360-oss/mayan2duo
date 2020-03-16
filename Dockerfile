FROM openjdk:11-jdk
VOLUME /tmp
ADD /build/libs/*.jar /mayan2duo-service.jar
ENV SPRING_PROFILES_ACTIVE=production
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mayan2duo-service.jar"]
