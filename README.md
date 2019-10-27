# Shopping_Rest

Follow the below steps to execute the project.

1) Follow the instruction given in https://gradle.org/install/ for gradle installation.
2) Go to build.gradle file location and execute 'gradle clean build' commond.
3) After the build is success, execute the 'gradle bootRun' commond in project location.
4) Use 'http://localhost:8080/welcome' to access the application.
5) Use 'http://localhost:8080/swagger-ui.html' to access swagger-ui for test endpoint. user Swagger UI for testing the endpoints/rest api endpoints.
6) To access DB use http://localhost:8080/h2-console/

  url=jdbc:h2:mem:testdb
  driverClassName=org.h2.Driver
  username=root
  password=

