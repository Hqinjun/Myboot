FROM java:8

MAINTAINER Hqinjun Hqinjun@xxx.com

VOLUME /tmp

ADD myboot-0.0.1-SNAPSHOT.jar boot.jar

RUN bash -c 'touch /boot.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 12345

ENTRYPOINT ["java", "-jar", "boot.jar"]
