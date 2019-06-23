

clone repo

run 
```mvn package```

then run

```java -jar target/notes-0.0.1-SNAPSHOT.jar```

then fire up CURL

```curl -i -H "Content-Type: application/json" -X POST -d '{"body": "Pick up milk!!!"}' http://localhost:8080/api/notes```

```curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes/1```

```curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes```

```curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/api/notes?query=mil```
