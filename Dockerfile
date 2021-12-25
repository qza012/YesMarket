FROM openjdk:8-jre

ARG JAR_FILE=build/libs/YesMarket-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} yesmarket.jar

ENTRYPOINT ["java", \
"-Dspring.profiles.active=prod", \
"-Dspring.config.location=classpath:/application-prod.yaml,/secret/application-secret.yaml", \
"-jar", \
"/yesmarket.jar"]