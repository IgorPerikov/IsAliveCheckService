CheckIsAlive service
=======
This is repository with source code of monitoring service


Building
---------------

for building .jar file:
* clone repo
* launch "mvn package" from console from root folder of project(need maven installed)
* .jar file called "checkalive-0.1.jar" will be under /target

Launching
---------------

for launching service u need to have mysql db with empty schema(tables will created automatically), default name of schema is "monitoring", it is possible to change it (aswell as username and password) in application.properties file, which placed in src/main/resources/
"monitoring.configuration.max-size" is parameter, which indicates max possible size of queue. service will deny all new claims with 503 in this state
