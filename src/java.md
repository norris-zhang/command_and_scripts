- ### jenv commands
```
$ jenv add /Library/Java/JavaVirtualMachines/openjdk-14.0.1.jdk/Contents/Home
$ jenv versions
$ jenv global 14.0
$ jenv local 11
$ jenv shell openjdk64-1.8.0.252

```

- ### flyway clean and migrate
```shell
$ mvn clean flyway:clean flyway:migrate -Dflyway.user=lisa -Dflyway.password=password -Dflyway.url=jdbc:postgresql://localhost:5432/lisadb -Dflyway.cleanDisabled=false
$ mvn clean flyway:info -Dflyway.user=lisa -Dflyway.password=password -Dflyway.url=jdbc:postgresql://localhost:5432/lisadb
```

- ### Map.computeIfAbsent
```java
class C {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.computeIfAbsent(key, x -> compute(x));
    }
}
```

- ### specify spring profile
```shell
$ mvn xxx -Dspring.profiles.active=dev
```

- ### @Value default to empty string or null
```java
@Value("${something.something:}")
// or
@Value("${something.something:#{null}}")
```