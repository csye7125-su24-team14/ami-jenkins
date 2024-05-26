#!/bin/bash

# Update package lists
sudo apt update

# Upgrade installed packages
sudo apt upgrade -y

# Install OpenJDK 11
sudo apt install openjdk-11-jdk -y

# Add Jenkins repository key
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee /usr/share/keyrings/jenkins-keyring.asc > /dev/null

# Add Jenkins repository to the system's list of sources
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null

# Update package lists with Jenkins repository
sudo apt-get update

# Install fontconfig and OpenJDK 11 JRE (runtime environment)
sudo apt-get install fontconfig openjdk-11-jre -y

# Install Jenkins
sudo apt-get install jenkins -y

# Start Jenkins
sudo systemctl start jenkins
sudo systemctl enable jenkins

# Install required packages for adding Caddy repository
sudo apt install -y debian-keyring debian-archive-keyring apt-transport-https curl

# Add Caddy repository key
curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/gpg.key' | sudo gpg --dearmor -o /usr/share/keyrings/caddy-stable-archive-keyring.gpg

# Add Caddy repository to the system's list of sources
curl -1sLf 'https://dl.cloudsmith.io/public/caddy/stable/debian.deb.txt' | sudo tee /etc/apt/sources.list.d/caddy-stable.list

# Update package lists with Caddy repository
sudo apt update

# Install Caddy
sudo apt install caddy -y

# Update caddyfile as reverse proxy
echo "$DOMAIN {
    reverse_proxy localhost:8080
}" | sudo tee /etc/caddy/Caddyfile >/dev/null

# Restart Caddy
sudo systemctl restart caddy