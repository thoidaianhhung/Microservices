# Sử dụng hình ảnh JDK 17 chính thức của OpenJDK làm cơ sở
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR vào container
COPY target/blog-app-0.0.1-SNAPSHOT.jar app.jar

# Expose port mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

# Command để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
