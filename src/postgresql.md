- ### Find all the referencing tables of a table column
```roomsql
SELECT
    r.table_name, r.column_name 
FROM information_schema.constraint_column_usage       u
INNER JOIN information_schema.referential_constraints fk
           ON u.constraint_catalog = fk.unique_constraint_catalog
               AND u.constraint_schema = fk.unique_constraint_schema
               AND u.constraint_name = fk.unique_constraint_name
INNER JOIN information_schema.key_column_usage        r
           ON r.constraint_catalog = fk.constraint_catalog
               AND r.constraint_schema = fk.constraint_schema
               AND r.constraint_name = fk.constraint_name
WHERE
    u.column_name = 'id' AND
    u.table_catalog = 'ams' AND
    u.table_schema = 'public' AND
    u.table_name = 'account';
```

- ### linux install postgres
```shell
$ sudo yum install postgres-server -y
$ sudo yum install postgres -y

$ sudo postgresql-setup --initdb postgresql
$ sudo systemctl enable postgresql.service
$ sudo systemctl start postgresql.service
```

- ### alter password for the a new postgres server
```shell
# an OS user 'postgres' is created. You can view from
$ sudo cat /etc/passwd
# postgres user conf
$ sudo cat /var/lib/pgsql/data/pg_hba.conf # you will see the auth methods
# "local" is for Unix domain socket connections only
local   all             all                                     peer
# IPv4 local connections:
host    all             all             127.0.0.1/32            ident 
# IPv6 local connections:
host    all             all             ::1/128                 ident

# Change the ident to trust, m5 or password.
# then restart service
$ sudo systemctl restart postgresql.service


# log onto postgres
$ sudo -i -u postgres
$ psql
postgres=# alter user postgres PASSWORD '123321'
```

- ### PSQL client
```shell
$ psql –h <host> -p <port> -d <database> -U <username> -W
# -W means please prompt password input, and don’t need to ask the server whether a password is required. 
$ password:
$ <database>=>  

$ <database>=> \e 
# Opens editor to write sql 

$ <database>=> \timing 
# Switching timing 

$ <database>=> \? 
# Show all available commands 

$ <database>=> \l 
# List available databases. 

$ <database>=> \dt 
# Show tables. 

$ <database>=> \d table_name 
# Desc table_name 

$ <database>=> \dn 
# List available schemas. 

$ <database>=> \q 
# Quit 
```

- ### Useful scripts
```postgresql
create extension pgcrypto; 
create extension "uuid-ossp"; 
create user litehaus_provisioning WITH PASSWORD 'litehaus_provisioning'; 
ALTER USER litehaus_provisioning CREATEDB; 
alter user litehaus_provisioning superuser; 
create database litehaus_provisioning; 
grant ALL PRIVILEGES on database litehaus_provisioning to litehaus_provisioning; 
create database litehaus_provisioning; 
drop database litehaus_provisioning; 
select encode(digest('blah', 'sha1'), 'hex'); 
select * from pg_roles; 
select * from pg_authid; 
```
