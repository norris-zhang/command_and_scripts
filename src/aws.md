- Get caller identity
```shell
$ aws sts get-caller-identity
```

- list stacks
```shell
$ aws --region ap-southeast-2 cloudformation list-stacks \
      --stack-status-filter CREATE_COMPLETE UPDATE_COMPLETE \
      --query "StackSummaries[?contains(StackName, 'MN-ShortUrl-Service')].StackName"
```
