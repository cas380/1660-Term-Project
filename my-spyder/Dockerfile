# Dockerfile for microservices-gui
FROM python

# Update package list
RUN apt update
RUN apt install -y libgl1-mesa-glx
RUN apt install -y spyder3

#RUN conda create -n spyder-env spyder=4 numpy scipy pandas matplotlib sympy cython

ENTRYPOINT spyder3

#CMD ["firefox", "-new-window", "https://mybinder.org/v2/gh/spyder-ide/spyder/4.x?urlpath=/desktop"]