# Dockerfile for microservices-gui
FROM python

# Update package list
RUN apt update
RUN apt install -y libgl1-mesa-glx
RUN apt install -y spyder3

ENTRYPOINT spyder3
