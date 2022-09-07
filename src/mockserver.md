- ### Install mockserver in docker
```shell
$ docker pull mockserver/mockserver
$ docker run -d --rm -P mockserver/mockserver
# -P tells Docker to map all ports exported by the MockServer container to dynamically allocated ports on the host machine
$ docker ps # to view dynamic ports
# to specify <host port>:<container port>
$ docker run -d --rm -p 1080:1080 mockserver/mockserver
```

- ### configure mockserver log level and server port
```shell
$ docker run -d --rm -p 1090:1090 --env MOCKSERVER_LOG_LEVEL=TRACE --env MOCKSERVER_SERVER_PORT=1090 mockserver/mockserver
```

- ### command line options
```shell
$ docker run --rm --name mockserver -p 1080:1090 mockserver/mockserver -logLevel INFO -serverPort 1090 -proxyRemotePort 443 -proxyRemoteHost mock-server.com
```

- ### map configuration
```shell
# create file `mockserver.properties` in the current folder and map it to `/config`
$ chmod o+r $(pwd)/mockserver.properties
$ docker run -v $(pwd):/config -p 1080:1080 mockserver/mockserver -serverPort 1080
```
/config/initializerJson.json
```json
[
  {
    "httpRequest": {
      "path": "/simpleFirst"
    },
    "httpResponse": {
      "body": "some first response"
    }
  },
  {
    "httpRequest": {
      "path": "/simpleSecond"
    },
    "httpResponse": {
      "body": "some second response"
    }
  }
]
```

- ### docker compose
```yaml
version: "2.4"
services:
  mockServer:
    image: mockserver/mockserver:5.14.0
    ports:
      - 1080:1080
    environment:
      MOCKSERVER_PROPERTY_FILE: /config/mockserver.properties
      MOCKSERVER_INITIALIZATION_JSON_PATH: /config/initializerJson.json
      MOCKSERVER_SERVER_PORT: 1080
    volumes:
      - type: bind
        source: .
        target: /config
```

- ### dashboard url
`http://localhost:1080/mockserver/dashboard`
