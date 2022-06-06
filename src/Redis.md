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

- ### purge
```shell
redis-cli> flushall
# or
redis-cli> flushdb
```

- ### list all keys
```shell
redis-cli> KEYS
# with wildcard
redis-cli> KEYS *name*
redis-cli> KEYS a??
```

- ### type of a key
```shell
redis-cli> type key
# zset is SortedSet
redis-cli> zrange key
```

- ### Data type: Hash
```shell
# Hash is map
# HMSET multiple set
$ HMSET user:1000 username antirez password P1pp0 age 34
# get all items of a key
$ HGETALL user:1000
# set one k-v into key user:1000
$ HSET user:1000 password 12345
# get all items of a key
$ HGETALL user:1000
```

- ### Data type: Set
```shell
# list all items of a set
$ smembers key
$ sadd key "value"
```
