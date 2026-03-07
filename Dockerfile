# [1단계] 빌드 스테이지
FROM amazoncorretto:17 AS builder
WORKDIR /app

# 캐시 효율을 위해 빌드 파일 먼저 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 의존성 미리 다운로드
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스 코드 복사 및 빌드
COPY src src
RUN ./gradlew bootJar -x test --no-daemon

# [2단계] 실행 스테이지
FROM amazoncorretto:17-alpine
WORKDIR /app
RUN apk add --no-cache curl

# 빌드 스테이지에서 실행 가능한 JAR만 명확하게 가져오기
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

# 기본 JVM 옵션 (EC2 t3.micro 권장: 512MB ~ 1GB)
ENV JAVA_OPTS="-Xms512m -Xmx512m -Dfile.encoding=UTF-8 -Duser.timezone=Asia/Seoul"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]