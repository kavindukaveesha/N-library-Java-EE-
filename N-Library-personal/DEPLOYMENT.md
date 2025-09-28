# N-Library Personal - Deployment Guide

## Prerequisites
- Apache Tomcat 8.5 or 9.0
- MySQL Server 5.7+
- Java 8

## Database Setup
1. Create a MySQL database named `library_manage`
2. Update database credentials in `src/java/com/team2/controller/utill/DBConnection.java` if needed:
   - URL: `jdbc:mysql://localhost:3306/library_manage`
   - Username: `root`
   - Password: (empty by default)

## Build Instructions
```bash
# Clean and compile
ant clean compile

# Build WAR file
ant dist
```

## Deployment
1. Copy `dist/N-Library-personal.war` to Tomcat's `webapps` directory
2. Start Tomcat server
3. Access the application at: `http://localhost:8080/N-Library-personal/`

## Application Features
- User authentication (Admin/Student roles)
- Book management
- Borrowing system
- User management
- Book categories management

## Login URLs
- Authentication: `/login`
- Admin Home: `/admin/home`
- Student Home: `/student/home`

## Libraries Used
- MySQL Connector J 8.3.0
- Google Gson 2.10.1
- Java Servlet API 3.1.0
- JSTL 1.2