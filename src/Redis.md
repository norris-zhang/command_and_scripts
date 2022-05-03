- ### Install Redis
```shell
$ brew install redis
```

- ### start server
```shell
$ redis-server
# to start in background
$ brew services start redis
$ brew services info redis
# to stop the service
$ brew service stop redis
```

- ### redis-cli
```shell
# open Redis REPL
$ redis-cli
127.0.0.1:6379
$ redis-cli -h redis15.localnet.org -p 6390 -a password PING
PONG
# or use -u uri redis://user:password@host:port/dbnum
$ redis-cli -u redis://LJenkins:p%40ssw0rd@redis-16379.hosted.com:16379/0 PING
PONG
```
