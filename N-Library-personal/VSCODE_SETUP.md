# VS Code Setup Guide for N-Library Java EE Application

## Prerequisites

### 1. Install Required VS Code Extensions
Open VS Code and install these extensions (or use the recommended extensions from `.vscode/extensions.json`):

- **Extension Pack for Java** (vscjava.vscode-java-pack)
- **Tomcat for Java** (adashen.vscode-tomcat)
- **Maven for Java** (vscjava.vscode-maven)

### 2. Install Required Software
- **Java 8 or 11** (JDK)
- **Apache Maven** (for modern build management)
- **Apache Tomcat 9.0** (application server)
- **MySQL Server 5.7+** (database)

## Setup Instructions

### Step 1: Open Project in VS Code
```bash
cd "/Users/kavindu/Developer/Software Enginner/Projects/N-library-Java-EE-/N-Library-personal"
code .
```

### Step 2: Install Extensions
When you open the project, VS Code should prompt you to install recommended extensions. Click "Install All" or install them manually from the Extensions marketplace.

### Step 3: Configure Java
1. Press `Cmd+Shift+P` (macOS) or `Ctrl+Shift+P` (Windows/Linux)
2. Type "Java: Configure Classpath"
3. Verify that VS Code detects your Java installation

### Step 4: Build Options

#### Option A: Using Maven (Recommended for VS Code)
```bash
# Clean and compile
mvn clean compile

# Package as WAR
mvn package

# The WAR file will be in target/N-Library-personal.war
```

#### Option B: Using Ant (Original build system)
```bash
# Clean and compile
ant clean compile

# Build WAR
ant dist

# The WAR file will be in dist/N-Library-personal.war
```

### Step 5: Database Setup
1. Create MySQL database: `library_manage`
2. Update database credentials in `src/java/com/team2/controller/utill/DBConnection.java` if needed

### Step 6: Deploy to Tomcat

#### Method 1: Manual Deployment
1. Copy the WAR file to Tomcat's `webapps` directory:
   ```bash
   cp target/N-Library-personal.war /path/to/tomcat/webapps/
   ```
2. Start Tomcat
3. Access: `http://localhost:8080/N-Library-personal/`

#### Method 2: Using VS Code Tomcat Extension
1. Install "Tomcat for Java" extension
2. Open Command Palette (`Cmd+Shift+P`)
3. Type "Tomcat: Add Tomcat Server"
4. Browse to your Tomcat installation directory
5. Right-click the WAR file in Explorer and select "Run on Tomcat Server"

## VS Code Features

### Building
- **Keyboard Shortcut**: `Cmd+Shift+P` → "Tasks: Run Build Task"
- **Maven**: Use `maven-compile` or `maven-package` tasks
- **Ant**: Use `ant-compile` or `ant-build-war` tasks

### Debugging
1. Set breakpoints in your Java code
2. Press `F5` or use "Run and Debug" panel
3. Select "Launch N-Library Application" configuration

### IntelliSense
- Auto-completion for Java code
- Error highlighting
- Quick fixes and refactoring

## Troubleshooting

### Issue: "Cannot resolve symbol" errors
**Solution**: 
1. Press `Cmd+Shift+P` → "Java: Reload Projects"
2. Check that all JAR dependencies are in classpath

### Issue: Build fails
**Solution**:
1. Ensure Java 8+ is installed
2. Check that all dependencies are available
3. Try running `mvn clean compile` or `ant clean compile`

### Issue: Tomcat deployment fails
**Solution**:
1. Ensure Tomcat is properly installed
2. Check that port 8080 is available
3. Verify database connection settings

## File Structure
```
N-Library-personal/
├── .vscode/                 # VS Code configuration
│   ├── settings.json       # Project settings
│   ├── tasks.json          # Build tasks
│   ├── launch.json         # Debug configuration
│   └── extensions.json     # Recommended extensions
├── src/java/               # Java source files
├── web/                    # Web resources (JSP, CSS, JS)
├── pom.xml                 # Maven configuration
├── build.xml               # Ant configuration (legacy)
└── VSCODE_SETUP.md         # This file
```

## URLs
- Application: `http://localhost:8080/N-Library-personal/`
- Login: `http://localhost:8080/N-Library-personal/login`
- Admin Dashboard: `http://localhost:8080/N-Library-personal/admin/home`
- Student Dashboard: `http://localhost:8080/N-Library-personal/student/home`