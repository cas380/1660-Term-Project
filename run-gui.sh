#!/bin/bash

# The X server is native to Linux...
echo DISPLAY_IP=:0.0 > config/.env
echo XSRV=/tmp/.X11-unix:/tmp/.X11-unix/:ro >> config/.env

# Make sure nothing is already up (does nothing otherwise)
docker-compose --env-file config/.env down

# Build and run the containers
docker-compose --env-file config/.env build
docker-compose --env-file config/.env up
# The script will wait as the containers are running
# Once the user does ^C, th script will continue here
docker-compose --env-file config/.env down

exit