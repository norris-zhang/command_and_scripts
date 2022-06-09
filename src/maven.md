- ### deploy jar file to central server
```shell
$ mvn deploy:deploy-file -DgroupId=net.bulletin \
  -DartifactId=provisioning-springsecurity \
  -Dversion=1.1.0-SHIM \
  -Dpackaging=jar \
  -Dfile=target/provisioning-springsecurity-1.1.0-SHIM.jar \
  -DrepositoryId=aws-release \
  -Durl=https://messagemedia.jfrog.io/messagemedia/libs-releases-local
```
- ### Install 3rd party JARs
```shell
$ mvn install:install-file -Dfile=<path-to-file> \
  -DgroupId=<group-id> \
  -DartifactId=<artifact-id> \
  -Dversion=<version> \
  -Dpackaging=<packaging>
```
- ### If there's a pom-file as well, you can install it with the following command
```shell
$ mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pomfile>
```

- ### predefined properties:
  https://web.archive.org/web/20150520200505/https://docs.codehaus.org/display/MAVENUSER/MavenPropertiesGuide
```
${pom.parent.version}
```

- ### disable enforcer
  Enforcer plugin raises dependency convergence warnings and errors.
```shell
$ mvn clean install -Denforcer.skip=true -Dskip.analyze=true -Dmaven.test.skip=true
```

- ### disable checkstyle and pmd
```shell
$ mvn clean install -Dmaven.test.skip=true -Dskip.analyze=true -Dcheckstyle.skip=true -Dpmd.skip=true
```

- ### disable spotbugs
```shell
$ mvn clean install -Dmaven.test.skip=true -Dskip.analyze=true -Dcheckstyle.skip=true -Dpmd.skip=true -Dspotbugs.skip=true
```