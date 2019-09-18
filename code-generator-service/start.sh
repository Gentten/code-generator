# 删除容器
docker rm -f code-generator
# 删除镜像
docker rmi -f code-generator/code-generator
# 创建镜像
docker build -t code-generator/code-generator  /opt/code-generator
# 运行容器
docker run  -d -p 8888:8888  --name code-generator code-generator/code-generator
# 睡眠 1m  等待启动
sleep 30s
# 查看最近10m中的日志 以检查启动情况
docker logs --since 10m code-generator