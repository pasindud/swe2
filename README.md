mvn install
mvn spring-boot:run 

- Get the token

```
	curl --noproxy localhost -u user:password -v http://localhost:8080/api/auth
	export AUTH_TOKEN=...
```

- Use the token to bypass security.

```
	curl --noproxy localhost -H "x-auth-token: $AUTH_TOKEN" -v http://localhost:8080/api/greet
```