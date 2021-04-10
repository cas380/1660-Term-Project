# Dockerfile for my-browser
# Deprecating this and moving to gui container...

# Linux image we will use
FROM ubuntu:18.04

# Update our package manager so it can find the packages we need
RUN apt-get update

# need browser
RUN apt-get install -y firefox

# Install the thing to get Java webpage loading to work
RUN apt-get install -y libgnome2-0

# Set this up to be interacted with by the GUI via SSH
# https://codeburst.io/direct-connection-to-a-docker-container-with-ssh-56e1d2744ee5
RUN apt-get install -y ssh
RUN service ssh start
# service start doesnt work outside a shell script???
RUN useradd -m -s /bin/bash -p $(openssl passwd -1 root) cas380

# Undo apt-get update to make image smaller?
RUN rm -rf /var/lib/apt/lists/*

# Leave container idling
# maybe then we can access firefox?
ENTRYPOINT ["tail", "-f", "/dev/null"]
# Use -d on your docker command to keep running in the background, apparently