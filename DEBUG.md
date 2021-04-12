# Debugging Docker
#### (This is a reference for me)

 * `docker inspect container_name` 

   - This command will display information about the given container. It can be used to find the network IP of a container 
(which is needed in order for containers to talk to each other). 

 * `docker exec -it container_name bash`

   - This command will allow you to access a container and check it out like a shell.

Since my containers are attached to a user-defined network `gui-to-www` in `docker-compose.yml`, the containers can 
use **automatic service discovery** to resolve each other's IPs. This way, I don't have to use `docker inspect`. 
If I were to `exec` into my GUI container and run `firefox RStudio:8787`, it would automatically translate to 
something like `firefox 172.24.0.3:8787`.

 * `docker ps -a`
 
   - Lists ALL containers (not just the running ones).

 * `docker rm NAME_OR_ID`
 
   - Removes the corresponding container.

 * `docker rmi NAME_OR_ID`
 
   - Removes the corresponding image.
   
 * `docker run -e DISPLAY=:0 -v /tmp/.X11-unix:/tmp/.X11-unix/:ro 1660-term-project_microservices-gui`

   - Run GUI container on its lonesome

 * `xterm -hold -e ls -li`

   - Runs the command "ls -li" in a new xterminal. `-hold` prevents the terminal from closing on completion of the command.

 * `docker ps -a -q -f status=exited | xargs docker rm`

   - Remove all stopped containers.

 * `docker image prune -a`

   - Remove all dangling images.

 * `tsm version -s https://localhost:8850`

   - Ensure Tableau CLI is working.