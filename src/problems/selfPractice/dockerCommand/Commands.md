Here’s a summary of what we’ve done so far regarding managing your MySQL Docker container and interacting with MySQL:

### 1. **Docker Container Management**

- **Started MySQL Container:**
    - You used the `docker start mysql` command to start the MySQL container.

- **Checked Container Status:**
    - `docker ps` showed that the container was running.
    - `docker ps -a` confirmed that the container was up and running for 14 minutes.
    - `docker inspect --format '{{.State.Status}}' mysql` showed that the container status is "running".

- **Handling Container Commands:**
    - You attempted to run Docker commands (e.g., `docker logs mysql`, `docker stop mysql`) from within the MySQL CLI, which resulted in errors. Docker commands should be executed from your host terminal.

### 2. **MySQL CLI Interaction**

- **Accessed MySQL CLI:**
    - Used the command `docker exec -it mysql mysql -u root -p` to enter the MySQL CLI.

- **Executed SQL Commands:**
    - **Listed Databases:**
      ```sql
      SHOW DATABASES;
      ```
    - **Selected Database:**
      ```sql
      USE bootcamp;
      ```
    - **Listed Tables in Database:**
      ```sql
      SHOW TABLES;
      ```
    - **Queried Tables:**
      ```sql
      SELECT * FROM Courses;
      SELECT * FROM Enrollments;
      SELECT * FROM Grades;
      SELECT * FROM Students;
      ```

- **Encountered and Fixed Errors:**
    - Fixed syntax errors like `show Databse` (should be `SHOW DATABASES;`) and extra semicolons in queries (`select * from Enrollments;;`).
    - Resolved issues with table names being case-sensitive.

### 3. **Commands and Status**

- **Docker Commands:**
    - **To List Running Containers:**
      ```bash
      docker ps
      ```
    - **To List All Containers (Running and Stopped):**
      ```bash
      docker ps -a
      ```
    - **To Inspect Container Status:**
      ```bash
      docker inspect --format '{{.State.Status}}' mysql
      ```

- **MySQL CLI Commands:**
    - **To Exit MySQL CLI:**
      ```sql
      exit;
      ```

- **Container Status Summary:**
    - Your MySQL container is currently running and functioning properly.

### Next Steps

1. **Stop the Container (if needed):**
   ```bash
   docker stop mysql
   ```

2. **Restart the Container (if needed):**
   ```bash
   docker restart mysql
   ```

3. **Remove the Container (after stopping it):**
   ```bash
   docker rm mysql
   ```

4. **View Logs (for troubleshooting or monitoring):**
   ```bash
   docker logs mysql
   ```

5. **Reconnect to MySQL CLI (if needed):**
   ```bash
   docker exec -it mysql mysql -u root -p
   ```

----------------------------------------------

### 1. **Starting a New MySQL Container**

The `docker run` command is used to create and start a new Docker container. Here’s the command you’ve provided:

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=password123 -p 3306:3306 bitnami/mysql:latest
```

- **`--name mysql`**: Names the container `mysql`.
- **`-e MYSQL_ROOT_PASSWORD=password123`**: Sets the environment variable `MYSQL_ROOT_PASSWORD` to `password123`, which defines the root password for MySQL.
- **`-p 3306:3306`**: Maps port `3306` on your host machine to port `3306` on the container, allowing you to access MySQL through `localhost:3306`.
- **`bitnami/mysql:latest`**: Specifies the Docker image to use, in this case, the latest version of the Bitnami MySQL image.

### 2. **Connecting to MySQL from the Host Machine**

The `mysql` command you provided is used to connect to the MySQL server running in the Docker container:

```bash
mysql -u root -p -h 127.0.0.1 -P 3306
```

- **`-u root`**: Specifies the MySQL user as `root`.
- **`-p`**: Prompts for the MySQL password (which you set to `password123` in the Docker command).
- **`-h 127.0.0.1`**: Specifies the host as `127.0.0.1`, which is the localhost address.
- **`-P 3306`**: Specifies the port number as `3306`, which is the default MySQL port.

### **Putting It All Together**

Here’s a step-by-step guide to using these commands:

1. **Start a New MySQL Container:**

   Open your terminal and run:

   ```bash
   docker run --name mysql -e MYSQL_ROOT_PASSWORD=password123 -p 3306:3306 bitnami/mysql:latest
   ```

   This command will pull the Bitnami MySQL image if it’s not already downloaded and start a new container named `mysql`. The container will run MySQL with `password123` as the root password.

2. **Connect to MySQL from Your Host Machine:**

   In a new terminal window, run:

   ```bash
   mysql -u root -p -h 127.0.0.1 -P 3306
   ```

   You’ll be prompted to enter the password. Enter `password123` (the password you specified in the `docker run` command).

### **Verifying the Container Status**

After running the `docker run` command, you can verify that the container is running with:

```bash
docker ps
```

This will show you a list of running containers. You should see your `mysql` container in the list.

### **Stopping and Removing the Container**

If you need to stop and remove the container:

- **Stop the Container:**
  ```bash
  docker stop mysql
  ```

- **Remove the Container:**
  ```bash
  docker rm mysql
  ```
