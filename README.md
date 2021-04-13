Assumptions:
project folder named 1660-term-project
An X server is running (may need to run xinit?)
Need docker and docker-compose
change runProgram hardcoded env values

# Running on Linux

I started this project by developing on my Windows laptop and quickly realized I didn't have enough disk space 
to run all the necessary containers at once. "That's okay," I thought, "I'll just use the PC downstairs." 
Unfortunately, the PC downstairs does not have access to Hyper-V (it literally cannot run Docker) because 
it is Windows 10 Home edition. In the end, I had to use a Linux VM on the PC to use Docker, so this is 
how you run my project on Linux (Ubuntu)!

I've created a bash script `run-gui.sh` which connects to the native X server on Linux and runs the necesary 
docker commands. This should work across Linux machines, but if it fails for any reason, you can consult the 
manual steps below. You can run the script by doing `./run-gui.sh` in the root directory.

## Running manually on Linux

If the script doesn't work, here's the steps it goes through that you can do manually:
1. Assign the correct values to variables in `.env` (found in `config`)
   * I think these values could be wrong if you don't have default installations, though I don't know what else they would be
   * In my VM, `DISPLAY_IP`, which is used to set the `DISPLAY` variable of containers is set like so: `DISPLAY_IP=:0.0`
   * In my VM, `XSRV`, which is used to connect to the X server of your machine, is set like so: `XSRV=/tmp/.X11-unix:/tmp/.X11-unix/:ro`
   * I also had to run `xhost +` in order to give my containers the proper access control to the X server
2. Run `docker-compose --env-file config/.env build` to create the images
3. Run `docker-compose --env-file config/.env up` to run the containers
4. When finished, run `docker-compose --env-file config/.env down` to clean up the containers


*Below is the instructions for Windows, which I cannot verify works (beyond the first container, though my edits on Linux may have broken that as well).*


# Running on Windows (deprecated)

I've created a batch script `run-gui.bat` that sets up the X11 server and runs all the necessary docker commands. 
I don't know how portable it is from my machine, so if it fails for any reason, you can consult the manual steps below. 
You can run the script by doing `.\run-gui.bat` in the root directory.

## Running manually on Windows (deprecated)

If the script doesn't work, here's the steps it goes through that you can do manually:
1. Launch your X11 server
   * In the script, this is done by starting the `server.xlaunch` file in `config`.
2. Assign the correct IP to the `DISPLAY` variable in `.env` (also found in `config`)
   * The IPv4 can be found by running `ipconfig`
   * In my experience, the correct IP is that of WSL, for example: `DISPLAY_IP=172.19.224.1:0.0` **(The `:0.0` is critical!)**
3. Run `docker-compose --env-file config/.env build` to create the images
4. Run `docker-compose --env-file config/.env up` to run the containers
5. When finished, run `docker-compose --env-file config/.env down` to clean up the containers
