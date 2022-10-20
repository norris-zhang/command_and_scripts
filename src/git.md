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