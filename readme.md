CheckIsAlive service
=======
This is repository with source code of monitoring service


Preparation
---------------
For launching service u need to have mysql db with empty schema(tables will created automatically), default name of schema is "monitoring", it is possible to change it (aswell as username and password) in application.properties file, which placed in src/main/resources/

Building
---------------

For building .jar file:
* launch "mvn package" from console from root folder of project (need maven installed)
* .jar file called "checkalive-0.1.jar" will be under /target

Launching
---------------

To launch service, u need to simply call "java -jar checkalive-0.1.jar" from your favourite console, service will be available at localhost:8080/api/

Api
---------------
* /application-check POST - post a claim
* /application-status?host=192.168.0.1&port=80&path=%2Fping GET - get a result

Configuration
---------------

"monitoring.configuration.max-size" is parameter, which indicates max possible size of queue. Service will deny all new claims with 503 in this state


```
<plugin>
    <groupId>ru.agentguru</groupId>
    <artifactId>build-info-maven-plugin</artifactId>
    <version>0.1</version>
</plugin>
```
