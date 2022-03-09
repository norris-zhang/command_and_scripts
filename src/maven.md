- deploy jar file to central server
```
$ mvn deploy:deploy-file -DgroupId=net.bulletin \
  -DartifactId=provisioning-springsecurity \
  -Dversion=1.1.0-SHIM \
  -Dpackaging=jar \
  -Dfile=target/provisioning-springsecurity-1.1.0-SHIM.jar \
  -DrepositoryId=aws-release \
  -Durl=https://messagemedia.jfrog.io/messagemedia/libs-releases-local
```

- predefined properties:
  https://web.archive.org/web/20150520200505/https://docs.codehaus.org/display/MAVENUSER/MavenPropertiesGuide
```
${pom.parent.version}
```