- ### docker compose up with --detach (-d) to run in background
```shell
$ docker compose up -d
```
- ### docker service
```shell
# in docker-compose.yml
# each service is like a computer.
# They have their own IP address.
# Their host names are their service names.
$ docker-compose exec sqs sh
# This will sh into the service sqs. Inside it, you will be able to test connectivity to other services.
# But these IPs are not accessible from outside of the container.
```

