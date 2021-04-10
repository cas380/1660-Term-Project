@ECHO off

:: Open our X11 server
START config/server.xlaunch
:: Will generate an error if a server is already running

:: Find the X11 IP to use in the docker commands
SET candidate_ip="0.0.0.0"

SET ip_address_string="IPv4 Address"
for /f "usebackq tokens=2 delims=:" %%f in (`ipconfig ^| findstr /c:%ip_address_string%`) do (
    echo Potential IP: %%f
    SET candidate_ip=%%f
)
:: Get rid of the space at character 0
set candidate_ip=%candidate_ip:~1%:0.0
echo Using IP %candidate_ip% for X11

:: Put this IP in the .env file
echo DISPLAY_IP=%candidate_ip% > config/.env

:: Make sure nothing is already up (does nothing otherwise)
docker-compose --env-file config/.env down

:: Build and run the containers
docker-compose --env-file config/.env build
docker-compose --env-file config/.env up
:: The script will wait as the containers are running
:: Once the user does ^C, th script will continue here
docker-compose --env-file config/.env down

EXIT