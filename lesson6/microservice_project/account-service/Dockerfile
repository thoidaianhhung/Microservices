# Sử dụng image cơ sở Java
FROM openjdk:17-jdk

# Tạo thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào container
COPY target/account-service-0.0.1-SNAPSHOT.jar /app/account-service-0.0.1-SNAPSHOT.jar

# Câu lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/account-service-0.0.1-SNAPSHOT.jar"]