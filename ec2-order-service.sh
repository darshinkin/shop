#!/bin/bash
# Use this for your user data (script from top to bottom)
# install httpd (Linux 2 version)
yum update -y
yum install -y httpd
systemctl start httpd
systemctl enable httpd
echo "<h1>Hello World from $(hostname -f)</h1>" > /var/www/html/index.html

# install docker
yum install -y docker
# set permissions for current user
groupadd docker
usermod -aG docker $USER
newgrp docker 
# start docker
systemctl start docker
# retrieve order-origin image
docker pull arshinkinda/order-origin
# install docker-compose
curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
# docker-compose.yml
echo "version: '3.9'
services:
  order:
    image: arshinkinda/order-origin
    container_name: order
    restart: unless-stopped
    ports:
      - 8084:8084
    environment:
      AWS_REGION: us-east-1
      AWS_SQS_QUEUE: OrderQueue
      AWS_ACCESS_KEY_ID: AKIAUQORHSFSTCEZNM4E
      AWS_SECRET_ACCESS_KEY: 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" > docker-compose.yml
docker-compose up -d