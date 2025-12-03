- ### class loaders
When you call Thread.currentThread().getContextClassLoader(), \
In a web server, such as Jetty, it is normally WebAppClassLoader which loads classes from WEB-INF/classes and WEB-INF/lib. \
In a standalone Java application, it is normally the AppClassLoader which loads classes from the classpath.

- ### jenv commands
```shell
$ jenv add /Library/Java/JavaVirtualMachines/openjdk-14.0.1.jdk/Contents/Home
$ jenv versions
$ jenv global 14.0
$ jenv local 11
$ jenv shell openjdk64-1.8.0.252
# install jdk
$ brew install openjdk@17
$ brew list jdk
$ jenv add /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/
```

- ### flyway clean and migrate
```shell
$ mvn clean flyway:clean flyway:migrate -Dflyway.user=lisa -Dflyway.password=password -Dflyway.url=jdbc:postgresql://localhost:5432/lisadb -Dflyway.cleanDisabled=false
$ mvn clean flyway:info -Dflyway.user=lisa -Dflyway.password=password -Dflyway.url=jdbc:postgresql://localhost:5432/lisadb
```

- ### Map.computeIfAbsent
```java
class C {
    static void main(String[] args) {
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
@Value("${something.something:}") String s;
// or
@Value("${something.something:#{null}}") String s;
```

- ### IntelliJ Hot Keys
```
Reformat Code: option+command+L / Ctrl+Alt+L
```
