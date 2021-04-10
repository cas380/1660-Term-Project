# Debugging Docker

 * `docker inspect container_name` 

   - This command will display information about the given container. It can be used to find the network IP of a container 
(which is needed in order for containers to talk to each other). 

 * `docker exec -it container_name bash`

   - This command will allow you to access a container and check it out like a shell.

Since my containers are attached to a user-defined network `gui-to-www` in `docker-compose.yml`, the containers can 
use **automatic service discovery** to resolve each other's IPs. This way, I don't have to use `docker inspect`. 
If I were to `exec` into my GUI container and run `firefox RStudio:8787`, it would automatically translate to 
something like `firefox 172.24.0.3:8787`.
