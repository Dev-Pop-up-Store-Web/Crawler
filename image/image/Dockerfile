# 베이스 이미지로 JDK 17 포함된 이미지 사용
#FROM openjdk:17 as build
FROM openjdk:17.0-slim
EXPOSE 8888
# 애플리케이션 디렉토리 설정
WORKDIR /app

# 애플리케이션 빌드 파일 복사
COPY build/libs/*.jar image.jar

# 필요한 라이브러리 설치
RUN apt-get update && apt-get install -y libglib2.0-0 libnss3 libxcb1 wget unzip
# 컨테이너에 드라이버 설치
RUN apt-get install -y chromium-common chromium-sandbox
RUN apt-get install -y chromium chromium-driver

# Chromedriver 복사
#RUN cp /usr/bin/chromedriver ./
#chmod +x chromedriver

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","image.jar"]
