# 1단계: 빌드 스테이지
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Gradle 캐싱 최적화를 위해 필요한 파일들만 먼저 복사
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./

# Gradle 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 종속성 다운로드 (캐시 활용)
RUN ./gradlew dependencies --no-daemon

# 소스 복사 (Gradle 종속성 캐시 활용)
COPY src src

# 애플리케이션 빌드 (테스트 제외)
RUN ./gradlew clean build -x test

# 2단계: 실행 환경 스테이지
FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
