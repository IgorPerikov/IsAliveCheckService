CheckIsAlive service
=======
This is repository with source code of monitoring service

for building .jar file launch "mvn clean package" from console from root folder of project(need maven installed)

for launching service u need to have mysql db with empty schema(tables will created automatically), default name of schema is "monitoring", it is possible to change it in application.properties file, which stored in src/main/resources/application.properties

also u need to set up here your own user/pw, if default is not ok

"monitoring.configuration.max-size" is parameter, which indicates max possible size of queue. service will deny all new claims with 503 in this state
