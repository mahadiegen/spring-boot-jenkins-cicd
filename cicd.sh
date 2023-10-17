mvn clean install
docker rm -f cicd-v1
docker rmi -f cicd

docker build -f Dockerfile -t cicd .

docker run --name cicd-v1 -d -p 8088:8088 cicd
