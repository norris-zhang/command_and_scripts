- grep to find string in files recursively
```shell
$ grep -r "keyword to look" /folder
```
-r or --recursive means to recursively search. But it will skip symbolic links that are encountered recursively.
Use -R if you want to follow all the symbolic links.