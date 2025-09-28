# Use Tomcat with JDK 17
FROM tomcat:10.1-jdk17-openjdk

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file to Tomcat webapps
COPY target/N-Library-personal.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]