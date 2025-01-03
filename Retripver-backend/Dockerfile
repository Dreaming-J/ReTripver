# 빌드를 위해 자바 17 이미지 사용
FROM openjdk:17-jdk-slim AS build

# 필수 패키지 설치 및 작업 디렉토리 설정
RUN apt-get update

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 및 Maven Wrapper 복사
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Maven 빌드 실행 (테스트 제외를 원하면 -DskipTests)
RUN chmod +x ./mvnw
RUN ./mvnw clean package

# 실행을 위해 자바 17 slim 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# jar파일 복사
COPY --from=build /app/target/*.jar app.jar

# JAR 파일 실행
ENTRYPOINT ["java", "-jar", "app.jar"]