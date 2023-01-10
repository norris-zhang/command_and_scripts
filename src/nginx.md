- ### Install Nginx
```shell
$ sudo apt update
$ sudo apt install nginx
```

- ### disable default virtual host
```shell
$ sudo unlink /etc/nginx/sites-enabled/default
```

- ### configure reverse proxy
/etc/nginx/sites-available/lisa.norriszhang.com
```
server {
    listen 80;
    listen [::]:80;

    server_name lisa.norriszhang.com;

    location / {
        proxy_set_header Institution-Value "1";
        proxy_set_header Institution-Text "LisaArt";
        proxy_pass http://localhost:8080;
        include proxy_params;
    }
}
```

- ### enable config
```shell
$ sudo ln -s /etc/nginx/sites-available/lisa.norriszhang.com /etc/nginx/sites-enabled/lisa.norriszhang.com
```

- ### test config
```shell
$ sudo nginx -t
```

- ### restart nginx
```shell
$ sudo systemctl restart nginx
# or
$ sudo service nginx restart
```
