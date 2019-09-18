# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
FROM fabric8/java-jboss-openjdk8-jdk

#中文
ENV LANG zh_CN.utf8

WORKDIR /opt/code-generator
#将当前jar 复制到opt根目录下
ADD code-generator-web-0.0.1-SNAPSHOT.jar  code-generator-web.jar
#将依赖包 复制到容器根目录/libs下
#8888 Docker镜像告知Docker宿主机应用监听了8090端口
EXPOSE 8888
#容器启动时执行的命令
CMD java -jar  code-generator-web.jar
