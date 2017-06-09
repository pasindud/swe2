- mvn install
- mvn spring-boot:run 

- SQL user info

That data is locate at main/resources/application.properties

- Get the token

```
	curl --noproxy localhost -u user:password -v http://localhost:8080/api/auth
	export AUTH_TOKEN=...
```

- Use the token to bypass security.

```
	curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v http://localhost:8080/api/greet
```


- Small cmd to remove table = " " from JPA modeler
 cd src/main/java/com/app/enties/
 sed -i '' "s/table \= \"[A-z]*\",//g" *.java
 sed -i '' "s/, table \= \"[A-z]*\"//g" *.java