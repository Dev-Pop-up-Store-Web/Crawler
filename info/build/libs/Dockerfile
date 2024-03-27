FROM openjdk:17.0-slim

# 필요한 라이브러리 설치
RUN rm -rf /var/lib/apt/lists/*
RUN dpkg --configure -a && apt --fix-broken install

# 필요한 라이브러리 설치
RUN apt-get update && apt-get install -y libglib2.0-0 libnss3 libxcb1 wget unzip
# 컨테이너에 드라이버 설치
RUN apt-get install -y chromium-common chromium-sandbox
RUN apt-get install -y chromium chromium-driver

# 컨테이너 경로 a -> b로 Chromedriver 복사
RUN cp /usr/bin/chromedriver /usr/local/bin/chromedriver

COPY InfoCrawler-0.0.1-SNAPSHOT.jar popup-info.jar

EXPOSE 27017

ENTRYPOINT ["java","-jar","/popup-info.jar"]

