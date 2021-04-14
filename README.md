### You can watch my demo video [here](https://youtu.be/OWfLsCELqoI)!

# Assumptions:
* Whoever is attempting to run this is doing so on Linux (I did it on Ubuntu)
* The project folder should be named `1660-Term-Project` (it will be after cloning from git)
* An X server is running (which should be the case by default but you may need to run `xinit`?)
* You have `docker` and `docker-compose`

# Running on Linux

I started this project by developing on my Windows laptop and quickly realized I didn't have enough disk space 
to run all the necessary containers at once. "That's okay," I thought, "I'll just use the PC downstairs." 
Unfortunately, the PC downstairs does not have access to Hyper-V (it literally cannot run Docker) "because 
it is Windows 10 Home edition" (wait a second... my laptop is too? *It* can run docker!). In the end, I had 
to use a Linux VM on the PC to use Docker, so this is how you run my project on Linux (Ubuntu)!

I've created a bash script `run-gui.sh` which connects to the native X server on Linux and runs the necesary 
docker commands. This should work across Linux machines, but if it fails for any reason, you can consult the 
manual steps below. You can run the script by doing `./run-gui.sh` in the root directory.

**IMPORTANT: Running the `run-gui.sh` script will overwrite the `config/.env` file with the values in `run-gui.sh` (this is done on lines 21 and 24 of the script, you can change the values there too if needed).**

**IMPORTANT: This was mentioned in the video; if `docker` doesn't work without `sudo`, you can run the commented commands in `run-gui.sh` (or presumably just insert `sudo` at the beginning of all the docker commands in the script) to fix the issue.**

## Running manually on Linux

If the script doesn't work, here's the steps it goes through that you can do manually:
1. Assign the correct values to variables in `.env` (found in `config`)
   * I think these values could be wrong if you don't have default installations, though I don't know what else they would be
   * In my VM, `DISPLAY_IP`, which is used to set the `DISPLAY` variable of containers is set like so: `DISPLAY_IP=:0.0`
   * In my VM, `XSRV`, which is used to connect to the X server of your machine, is set like so: `XSRV=/tmp/.X11-unix:/tmp/.X11-unix/:ro`
   * I also had to run `xhost +` in order to give my containers the proper access control to the X server
   * If you do end up needing to change `DISPLAY_IP` and `XSRV` for whatever reason, those two values are hardcoded in `runProgram()` in `GUI.java` of `microservices-gui` and will need changed there too.
2. Run `docker-compose --env-file config/.env build` to create the images
3. Run `docker-compose --env-file config/.env up` to run the containers
4. When finished, run `docker-compose --env-file config/.env down` to clean up the containers
