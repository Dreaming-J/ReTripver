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
RUN ./mvnw clean package -DskipTests

# 실행을 위해 자바 17 slim 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# wait-for-it 스크립트 다운로드 및 실행 권한 부여
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

# jar파일 복사
COPY --from=build /app/target/*.jar app.jar

# wait-for-it을 사용하여 DB 연결 확인 후 앱 시작
ENTRYPOINT ["/bin/sh", "-c", "./wait-for-it.sh db:3306 -t 180 -- java -jar app.jar"]