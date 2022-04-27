- ### grep to find string in files recursively
```shell
$ grep -r "keyword to look" /folder
```
-r or --recursive means to recursively search. But it will skip symbolic links that are encountered recursively.
Use -R if you want to follow all the symbolic links. \
If the keyword does not include spaces, the double quotes are optional.

- ### View listening ports:
```shell
$ sudo lsof -i -P -n | grep LISTEN
$ sudo lsof -i:22
```

- ### sed: stream edit. Edit file/string without opening the file
```shell
$ sed 's/unit/linux/g' <filename>
```
s: substitute or replace
unit: search keyword
linux: replacement
g: global - replace all. Default to only replace the first occurrence.
```shell
$ sed 's/unit/linux/3' <filename>
replace the 3rd occurrence of each line.
```
Reference:
https://www.geeksforgeeks.org/sed-command-in-linux-unix-with-examples/

- ### Find out IPs of a domain
```shell
$ dig +short google.com
```

- ### List all timezones and set timezone
```shell
$ sudo systemsetup -listtimezones
$ sudo systemsetup -listtimezones | grep -i sydney
$ sudo systemsetup -settimezone Australia/Sydney
```

