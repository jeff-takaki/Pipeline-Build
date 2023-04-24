FROM tomcat:8.5.51-jdk11-openjdk
COPY target/BookManagement.war /usr/local/tomcat/webapps/BookManagement.war