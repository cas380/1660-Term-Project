#!/bin/bash

takedown() {
    docker-compose --env-file config/.env down --remove-orphans
    # Containers created in JAVA could still be running if the user didn't close them
    docker container stop $(docker container ls -q --filter name=_JAVAGUI*)
}

# The X server is native to Linux...
xhost +
echo DISPLAY_IP=:0 > config/.env
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