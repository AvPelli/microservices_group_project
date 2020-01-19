from openjdk:8
COPY . /app
#RUN cd /app && ./mvnw package -DskipTests
CMD ["java", "-jar", "/app/target/sportarena-app-arena-staff-management-0.0.1-SNAPSHOT.jar", "-DpreferIPv4Stack"]