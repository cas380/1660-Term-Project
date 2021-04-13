#!/bin/bash

# Make docker available beyond sudo with:
# sudo usermod -aG docker cas380
# su - cas380

# You may need to xinit to run X server

takedown() {
    docker-compose --env-file config/.env down --remove-orphans
    # Containers created in JAVA could still be running if the user didn't close them
    containers=$(docker ps -a -q -f name=_JAVAGUI*)
    if [[ $containers != '' ]]; then
        echo Found some leftover Java containers...
        docker container stop $containers
    fi
}

# The X server is native to Linux...
xhost +
echo DISPLAY_IP=:0 > config/.env
# To find where XSRV is...
# lsof -U | grep X
echo XSRV=/tmp/.X11-unix:/tmp/.X11-unix/:ro >> config/.env

# Make sure nothing is already up (does nothing otherwise)
takedown

# Build and run the containers
docker-compose --env-file config/.env build
docker-compose --env-file config/.env up
# The script will wait as the containers are running
# Once the user does ^C, the script will continue here
takedown

exit