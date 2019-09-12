#删除容器
docker rm -f code-generator
#删除镜像
#docker rmi -f code-generator
# 创建镜像
docker build -t code-generator/code-generator  /opt/code-generator
#创建并允许容器
docker run  -p 8888:8888 --name code-generator code-generator/code-generator