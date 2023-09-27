# Libraries
spring-boot-starter-validation - to handle field validation<p>
jackson-dataformat-xml - to handle xml request and response<p>

# Tests
Unit test, Repository test and Integration test provided

# Valid APIs

POST - http://localhost:8080/retro
<p>
POST - http://localhost:8080/retro/bulk
<p>
POST - http://localhost:8080/retro/Retro2/feedback
<p>
PUT - http://localhost:8080/retro/Retro3/feedback

# Search Retros 
## can return xml or json based on Accept header param
GET - http://localhost:8080/retro/0/2
<p>
GET - http://localhost:8080/retro?date=01/12/2023