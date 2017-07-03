


```
./mvnw clean package -DskipTests=true && cf push
cf create-user-provided-service cf-event-listener -l https://cf-event-listener.cfapps.io/logs
cf bind-service myapp cf-event-listener
```