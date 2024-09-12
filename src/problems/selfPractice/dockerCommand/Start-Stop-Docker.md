### 1. **Start Docker Desktop Using `open` Command:**

```bash
open -a Docker
```

This will launch Docker Desktop if it is installed in the standard Applications folder.

### 2. **Check Docker Status:**

```bash
docker info
```

If Docker is running properly using the Docker CLI, this command will provide information about your Docker installation and its current status.

### 3. **Using `docker` Commands Directly:**

If Docker Desktop is already running, you can use various `docker` commands directly from your terminal to manage containers, images, and more. For example:
```bash
docker run hello-world
```
This command will run a simple Docker container to verify that Docker is working correctly. 

### 4. **Quit Docker Desktop Using AppleScript:**

```bash
osascript -e 'quit app "Docker"'
```
This command sends an AppleScript command to quit the Docker application.

- **Graceful Quit:** Use `osascript -e 'quit app "Docker"'`

### 5. **Alternative: Using `killall` Command**

If for some reason the AppleScript method doesn’t work, you can forcefully terminate Docker Desktop using the `killall` command. Note that this will terminate the application without allowing it to close gracefully:
```bash
killall Docker
```
This command will kill the Docker Desktop process. Use this with caution, as it may result in unsaved data or state being lost.
- **Force Quit (if necessary):** Use `killall Docker`

These commands should help you manage Docker Desktop from the terminal effectively.

If you want to completely quit Docker Desktop and ensure that its icon disappears from the taskbar, you'll need to use a method that not only stops the Docker engine but also quits the Docker Desktop application entirely. Here's how you can do this from the command line:

### 6. **Using `osascript` to Quit Docker Desktop:**

To make sure Docker Desktop quits completely, including removing its icon from the taskbar, you should use AppleScript to simulate the user action of quitting from the menu. Here's a more comprehensive AppleScript command that simulates clicking the "Quit Docker" option from the menu:

```bash
osascript -e 'tell application "Docker" to quit'
```
This command should quit Docker Desktop gracefully and remove it from the taskbar.

### 3. **Ensure Docker Desktop is Fully Quit:**

Sometimes, using the `killall` command might not immediately remove the icon from the taskbar because of macOS's way of handling application processes. In such cases, you might need to ensure that all related Docker processes are terminated. To check for Docker-related processes, you can use:

```bash
ps aux | grep Docker
```

Then, use `kill` to terminate any remaining processes if necessary:

```bash
kill -9 <PID>
```

Replace `<PID>` with the Process ID of the Docker-related processes you find.

### Summary

- **Graceful Quit:** Use `osascript -e 'tell application "Docker" to quit'`
- **Force Quit:** Use `killall Docker`
- **Check and Kill Processes:** Use `ps aux | grep Docker` and `kill -9 <PID>` if needed

Using these commands should help you fully quit Docker Desktop from the command line.