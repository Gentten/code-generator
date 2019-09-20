# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
FROM fabric8/java-jboss-openjdk8-jdk

USER root
#中文
ENV LANG en_US.utf8

WORKDIR /opt/code-generator
#将当前jar 复制到opt根目录下
ADD code-generator-service-0.0.1-SNAPSHOT.jar  code-generator-service.jar
#将依赖包 复制到容器根目录/libs下
#8888 Docker镜像告知Docker宿主机应用监听了8090端口
EXPOSE 8888
#容器启动时执行的命令
CMD java -jar  code-generator-service.jar
