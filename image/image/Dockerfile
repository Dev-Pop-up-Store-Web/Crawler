# 베이스 이미지로 JDK 17 포함된 이미지 사용
FROM openjdk:17 as build
EXPOSE 9387
# 애플리케이션 디렉토리 설정
WORKDIR /app

# 애플리케이션 빌드 파일 복사
COPY build/libs/*.jar image.jar
#COPY chromedriver.exe chromedriver
# 크롬 설치
USER root
# RUN apt-get update && apt-get install -y wget gnupg2 && \
#     wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
#     sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' && \
#     apt-get update && apt-get install -y google-chrome-stable
#
# # 크롬 드라이버 설치 (버전은 크롬 버전에 맞게 조정해야 할 수 있음)
RUN apt-get install -y chromium-common=112.0.5615.138-1~deb11u1 chromium-sandbox
RUN apt-get install -y chromium=112.0.5615.138-1~deb11u1 chromium-driver=112.0.5615.138-1~deb11u1

# Chromedriver 복사
RUN cp /usr/bin/chromedriver ./
chmod +x chromedriver

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","image.jar"]
