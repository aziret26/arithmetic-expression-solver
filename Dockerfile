FROM openjdk:11-jdk

COPY build/libs/calculator-app-*.jar /home/app.jar

CMD java -server \
    -jar -Dspring.profiles.active=docker /home/app.jar
