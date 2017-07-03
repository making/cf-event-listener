## CF Event Listener

```
./mvnw clean package -DskipTests=true && cf push
cf create-user-provided-service cf-event-listener -l https://cf-event-listener.cfapps.io/logs
cf bind-service myapp cf-event-listener
cf restart myapp
```


See [Streaming Application Logs to Log Management Services](http://docs.pivotal.io/pivotalcf/1-11/devguide/services/log-management.html) for more details.