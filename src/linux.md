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

- ### Count lines
```shell
$ wc -l
$ aws sqs list-queues | jq -r ".[]" | wc -l
```

- ### decode base64
```shell
$ openssl base64 -d [enter]
[paste encrypted code] [enter]
[ctrl + D]
[output]

$ openssl base64 [-e][enter]
[paste plain text][ctrl + D][enter]
[ctrl + D]
[output]

$ # in MacOS
$ echo -n "text" | base64
```

- ### Ubuntu auto remove to clear /boot space full
```shell
$ sudo apt autoremove
```

- ### AWS Linux Install java 17
```shell
$ sudo yum install java-17-amazon-corretto-headless # headless for server load. No GUI packages
$ sudo yum install java-17-amazon-corretto # headful version
$ sudo yum install java-17-amazon-corretto-devel #jdk
$ sudo yum install java-17-amazon-corretto-jmods #jmods
# The installation location is /usr/lib/jvm/java-17-amazon-corretto.<cpu_arch>.

# verification
$ sudo alternatives --config java

# uninstall
$ sudo yum remove java-17-amazon-corretto-headless
```

- ### xargs
xargs takes the previous command's result and pass it to the next command.
```shell
# This finds in the current directory and all sub-directories for any files whose names contain `fail.txt`
# Then these file names (paths) will be sent to command `cat` and in turn to `grep`.
$ find . | grep fail.txt | xargs -I {} cat {} | grep json
# xargs -I {} cat {}
# -I is defining {} as the placeholder
# so the previous command's output will be replacing {} in the following command. 
```
