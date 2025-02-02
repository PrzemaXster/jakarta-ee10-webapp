# modular-java-webapp

Personal webproject to learn about modular java web applications. The project is based on the following technologies: jakarta.servlet + tomcat 11, jakarta.ws.rs + jersey,
hibernate, spring (coming soon), spring security (coming soon), velocity (although I think I'm going to change it to thymeleaf once I get spring going).

## How to run the project
Setup Tomcat 11 and create a new server in your IDE. Add the project to the server and run it. The project should be available at `http://localhost:8080/*`, the suffix depends
on the name of the war file you deployed to tomcat.

## Current endpoints
- `/rest/myresource`: GET request that returns fetches ExamplePojo from h2db and returns a simple hello world message
- `/hello-servlet`: a simple servlet that returns a hello world message
- `/hello-velocity` : a simple velocity template servlet that returns a hello world message
