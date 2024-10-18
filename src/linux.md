- ### UUID Generator
```shell
$ uuidgen | tr 'A-Z' 'a-z'
87cfc59f-0403-47b1-8ef8-429501322cc8
```

### SSH file transfer
```shell
$ scp -i .ssh/key.pem -r /path/to/local/folder ec2-user@ip:/path/to/remote/folder
```

- ### curl and include http status code
```shell
$ curl -L -i https://example.com/path -H "Authorization: basic base64"
# -L, --location: redirect if informed by the server
# -i: include http status code and headers
```

- ### generate random password
```shell
$ openssl rand -base64 12
NR+xQ5jmLLSCVIkM
```
- ### bcrypt a password
```shell
$ htpasswd -nbB user examplepassword
user:$2y$05$Fi1T4kW1FZ2KQ9uhx12lWuBlm6r0.vKlavptrlPPmphhJ5k1AoBM6
# removing -b will result in asking the user to enter password, so password does not appear in the history.
$ htpasswd -nB user
New password:
Retry new password:
user:$2y$05$eQ1Y/eNEsG.t2.ho4zWrCOpkPn/xQEVEYlK5dA5G4ipeKMTHqjDVu
```

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

$ # to generate the Basic Authorization value providing username and password
$ echo -n "username:password" | base64
$ # -n makes sure that a newline is not added.
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
xargs takes the previous command's result and passes it to the next command.
```shell
# This finds in the current directory and all sub-directories for any files whose names contain `fail.txt`
# Then these file names (paths) will be sent to command `cat` and in turn to `grep`.
$ find . | grep fail.txt | xargs -I {} cat {} | grep json
# xargs -I {} cat {}
# -I is defining {} as the placeholder
# so the previous command's output will be replacing {} in the following command. 
```
