# Docker Setup for N-Library Java EE Application

## Prerequisites
- Docker and Docker Compose installed
- Java 17 (for local development)
- Maven (for building)

## Quick Start

### 1. Build the Application
```bash
mvn clean package
```

### 2. Run with Docker Compose
```bash
docker-compose up -d
```

### 3. Access the Application
- Application: http://localhost:8050
- Database: localhost:3306 (if needed for external access)

## Manual Docker Commands

### Build the Docker Image
```bash
docker build -t n-library-app .
```

### Run MySQL Container
```bash
docker run -d \
  --name n-library-db \
  -e MYSQL_ROOT_PASSWORD=Kavindu12345 \
  -e MYSQL_DATABASE=library_manage \
  -p 3306:3306 \
  -v mysql_data:/var/lib/mysql \
  mysql:8.0
```

### Run Application Container
```bash
docker run -d \
  --name n-library-app \
  --link n-library-db:mysql \
  -p 8050:8080 \
  n-library-app
```

## Database Configuration

The application connects to MySQL with these default settings:
- Host: mysql (in Docker network) / localhost (local)
- Port: 3306
- Database: library_manage
- Username: root
- Password: Kavindu12345

## Troubleshooting

### Check Container Logs
```bash
docker-compose logs app
docker-compose logs mysql
```

### Restart Services
```bash
docker-compose restart
```

### Clean Up
```bash
docker-compose down -v
```

## Default Login
- Username: admin
- Password: admin123

## File Structure
```
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
└── db-init/
    ├── 01-schema.sql
    └── 02-sample-data.sql
```