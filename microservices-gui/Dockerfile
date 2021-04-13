# Dockerfile for microservices-gui
FROM openjdk:8

# Copy over the java source
WORKDIR /root/term-project

# Update package list
RUN apt update

# Install necessary libraries for compilation
RUN apt install -y libxext6
RUN apt install -y libfontconfig1 libxrender1
RUN apt install -y libxtst6

# Get a browser (firefox) to run
RUN apt-get install -y firefox-esr

# Grab a terminal emulator too
RUN apt install -y xterm

# Install docker (yes really) so we can access other containers from this one
RUN apt-get update
RUN apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
RUN echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null
RUN apt-get update
RUN apt-get install -y docker-ce docker-ce-cli containerd.io

# Undo the apt update to reduce the image size
RUN rm -rf /var/lib/apt/lists/*

# Compile and run
COPY src/GUI.java /root/term-project
RUN javac GUI.java
ENTRYPOINT java GUI