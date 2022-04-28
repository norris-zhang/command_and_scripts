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

- ### from cloud formation to ec2 instance IPs
```shell
$ aws cloudformation describe-stack-resources --stack-name idm-dashboard-a | jq '.StackResources[]|select(.LogicalResourceId=="AMSGroup")|.PhysicalResourceId' -r | xargs aws autoscaling describe-auto-scaling-groups --auto-scaling-group-names | jq '.AutoScalingGroups[0].Instances[0].InstanceId' -r  | xargs aws ec2 describe-instances --instance-ids | jq '.Reservations[].Instances[].PrivateIpAddress' -r
10.25.26.211
$ ssh 10.25.26.211 cat /etc/tomcat7/server.xml | grep idm.jdbc
```

- ### CLI to local fake services
```shell
$ aws --endpoint-url http://localhost:9324 sqs list-queues
```

- ### send message to SQS
```shell
$ aws sqs send-message --queue-url <value> --message-body <value>
[--delay-seconds <value>]
[--message-attributes <value>]
[--message-system-attributes <value>]
[--message-deduplication-id <value>]
[--message-group-id <value>]
[--cli-input-json <value>]
[--generate-cli-skeleton <value>]
```

- ### receive message from SQS
```shell
$ aws sqs receive-message --queue-url <value>
[--attribute-names <value>]
[--message-attribute-names <value>]
[--max-number-of-messages <value>]
[--visibility-timeout <value>]
[--wait-time-seconds <value>]
[--receive-request-attempt-id <value>]
[--cli-input-json <value>]
[--generate-cli-skeleton <value>]
```