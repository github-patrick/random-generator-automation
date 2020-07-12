### To build the project

`mvn clean install` 

### To run the UI automation tests

`cd tests`

`mvn clean verify -P ui-tests`

####  To run the UI automation tests in a browser

`mvn clean verify -P ui-tests -Dspring.profiles.active=chrome-browser`
