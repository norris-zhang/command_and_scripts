- ### git diff stash
```shell
$ git stash show stash@{0} -p
# show will show the diffstat by default, but it accepts any formats known to git diff.
```

- ### delete a remote branch
```shell
$ git push origin --delete feature/branch-name
```

- ### bypass pre-push hook
```shell
$ git push origin HEAD --no-verify
```

- ### create a tag
```shell
$ git tag -a v4.1.0 abcde9f
$ git push origin v4.1.0
```

- ### revert a commit
```shell
# revert a normal commit
$ git revert hash
# revert a merge commit
$ git revert -m 1 hash
# A merge commit has two parents. If A is merged to B, B is parent 1, A is parent 2
# So this means revert hash based on commit B.
```

- ### config
```shell
# show all config
$ git config --list --global
# config
$ git config --global log.date local
```

- ### show local date in log
```shell
$ git log --date=local
```