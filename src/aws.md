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
{
    "QueueUrls": [
        "http://192.168.48.5:9324/queue/billing-notification-event-bus",
        "http://192.168.48.5:9324/queue/usage-aggregator-retry",
        "http://192.168.48.5:9324/queue/billing_usage_aggregation"
    ]
}
$ aws --endpoint-url http://localhost:9324 sqs receive-message --queue-url http://localhost:9324/queue/usage-aggregator-retry
# It is OK to use either localhost or 192.168.48.5 as the queue-url if the queues are in docker. Or use the docker container service name which is the domain name of the service.
```

- ### list queue with prefix
```shell
$ aws sqs list-queues --queue-name-prefix {prefix}
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

# send message from a file
$ aws sqs send-message --queue-url <value> --message-body file:///Users/norris/Documents/temp/xxx.json
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

- ### purge SQS queue
```shell
$ aws sqs purge-queue --queue-url http://localhost:9324/queue/usage-aggregator-retry
```

- ### get available message number
```shell
aws sqs get-queue-attributes --queue-url http://localhost:9324/queue/usage-aggregator-retry --attribute-names All
# valid attribute names: https://docs.aws.amazon.com/cli/latest/reference/sqs/get-queue-attributes.html
```

- ### retrieve data from a Kinesis Data Stream
```shell
$ aws kinesis list-streams
$ aws kinesis list-streams | grep -- ugapps-EventNotification- | xargs aws kinesis list-shards --stream-name
$ aws kinesis get-shard-iterator --shard-id shardId-000000000000 --stream-name ugapps-EventNotification-1XTROYB5W9DBP --shard-iterator-type LATEST | jq '.ShardIterator' -r | xargs aws kinesis get-records --shard-iterator
```

- ### aws sso login. Not sure why it wasn't working but now is working, but just keep a record here.
```shell
$ asp billing-dev
$ aws sso login
# then accept on the browser
$ aws s3 ls
```

- ### cfn plugin for IntelliJ
- cfn-lint
```shell
$ brew install cfn-lint
```

- ### aws ssm Session Manager
```shell
$ aws ssm start-session --target i-0bc44f783124b4465
```

- ### enable shell ability on ECS services
```yaml
TaskRole:
  Policies:
    - PolicyName: SshPolicy
      PolicyDocument:
        Statement:
          - Action:
              - "ssmmessages:CreateControlChannel"
              - "ssmmessages:CreateDataChannel"
              - "ssmmessages:OpenControlChannel"
              - "ssmmessages:OpenDataChannel"
            Effect: Allow
            Resource:
              - "*"
ECSService:
  Properties:
    EnableExecuteCommand: true
```
```shell
$ aws ecs execute-command --region us-west-2 \
    --cluster billing-service-cluster \
    --task $(aws ecs list-tasks --cluster billing-service-cluster --service billing-service --region us-west-2 --output text --query 'taskArns[0]') \
    --container billing-service \
    --command "curl -X POST --data DEBUG -H'Content-type: text/plain' http://localhost:8888/loglevels/com.messagemedia.zuora" \
    --interactive
```