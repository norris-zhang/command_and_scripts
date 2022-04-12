- ### Get caller identity
```shell
$ aws sts get-caller-identity
```

- ### list stacks
```shell
$ aws --region ap-southeast-2 cloudformation list-stacks \
      --stack-status-filter CREATE_COMPLETE UPDATE_COMPLETE \
      --query "StackSummaries[?contains(StackName, 'MN-ShortUrl-Service')].StackName"
```

- ### list stack resources
```shell
$ aws cloudformation list-stack-resources --stack-name st-restapi-account | jq '.StackResourceSummaries[] | select(.ResourceType|test("AWS::AutoScaling::AutoScalingGroup")).PhysicalResourceId' -r
```

- ### auto scaling group instances
```shell
$ aws autoscaling describe-auto-scaling-groups --auto-scaling-group-names "$ASG_NAME" | jq '.AutoScalingGroups[].Instances[].InstanceId' -r
```

- ### EC2 IP address
```shell
$ aws ec2 describe-instances --instance-ids "$INSTANCE" | jq '.Reservations[].Instances[].PrivateIpAddress' -r
```

- ### get SSM parameters
```shell
$ aws ssm get-parameter --name "/st-service-registry/account-coordinator/endpoint" | jq '.Parameter.Value' -r
```