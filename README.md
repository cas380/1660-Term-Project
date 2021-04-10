# Running on Windows

I've created a batch script `run-gui.bat` that sets up the X11 server and runs all the necessary docker commands. 
I don't know how portable it is from my machine, so if it fails for any reason, you can consult the manual steps below. 
You can run the script by doing `.\run-gui.bat` in the root directory.

## Running manually on Windows

If the script doesn't work, here's the steps it goes through that you can do manually:
1. Launch your X11 server
   * In the script, this is done by starting the `server.xlaunch` file in `config`.
2. Assign the correct IP to the `DISPLAY` variable in `.env` (also found in `config`)
   * The IPv4 can be found by running `ipconfig`
   * In my experience, the correct IP is that of WSL, for example: `DISPLAY_IP=172.19.224.1:0.0` **(The `:0.0` is critical!)**
3. Run `docker-compose --env-file config/.env build` to create the images
4. Run `docker-compose --env-file config/.env up` to run the containers
5. When finished, run `docker-compose --env-file config/.env down` to clean up the containers
