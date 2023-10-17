mvn clean install
sudo docker rm -f cicd-v1
sudo docker rmi -f cicd

sudo docker build -f Dockerfile -t cicd .

sudo docker run --name cicd-v1 -d -p 8088:8088 cicd
