# Dockerfile for microservices-gui
FROM python

# Update package list
RUN apt update
RUN apt install -y libgl1-mesa-glx
RUN pip install orange3

ENTRYPOINT orange-canvas