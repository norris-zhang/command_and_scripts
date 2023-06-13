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

- ### scan as opposed to keys
keys don't list some types of keys
```shell
redis-cli> scan 0 MATCH *name*
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
# get one field value
$ hget key field
# scan through Hash
$ hscan key 0 match *xxx* count 100
```

- ### Data type: Set
```shell
# list all items of a set
$ smembers key
$ sadd key "value"
```

- ### install redis-cli on ec2
```shell
$ sudo yum update
$ sudo yum install gcc
$ wget http://download.redis.io/redis-stable.tar.gz
$ tar xvzf redis-stable.tar.gz
$ cd redis-stable
$ make
$ src/redis-cli -c -h host -p port
```

- ### Redis databases
Redis has 16 out-of-box logical databases, indexed 0 to 15
```shell
cli> select 1
# switched to database 1
cli[1]> scan 0
```

- ### Lists operations
Lists are linked Strings. They can be used as a queue (LPUSH RPOP) or a stack (LPUSH LPOP)
```shell
cli> lpush key element [element...]
cli> lpop key [count]
# lrange return [start, end], both ends inclusive
cli> lrange key 0 -1
```