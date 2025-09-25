- ### docker compose up with --detach (-d) to run in background
```shell
$ docker compose up -d
```
- ### docker service
```shell
# in docker-compose.yml
# each service is like a computer.
# They have their own IP address.
# Their host names are their service names.
$ docker-compose exec sqs sh
# This will sh into the service sqs. Inside it, you will be able to test connectivity to other services.
# But these IPs are not accessible from outside of the container.

# this lists the running docker-compose.yml files
$ docker compose ls

# this lists the containers created by docker-compose.yml
$ docker compose ps
NAME                           IMAGE                       COMMAND                  SERVICE      CREATED             STATUS                       PORTS
billing-service-dynamo-1       alpine:latest               "sh -c 'apk add --no…"   dynamo       About an hour ago   Up About an hour             0.0.0.0:8000->8000/tcp, [::]:8000->8000/tcp
billing-service-localstack-1   localstack/localstack:4.5   "docker-entrypoint.sh"   localstack   About an hour ago   Up About an hour (healthy)   4510-4559/tcp, 4566/tcp, 5678/tcp
billing-service-redis-1        redis:7-alpine              "docker-entrypoint.s…"   redis        About an hour ago   Up About an hour             0.0.0.0:6379->6379/tcp, [::]:6379->6379/tcp
billing-service-sns-1          alpine:latest               "sh -c 'apk add --no…"   sns          About an hour ago   Up About an hour             0.0.0.0:9292->9292/tcp, [::]:9292->9292/tcp
billing-service-sqs-1          alpine:latest               "sh -c 'apk add --no…"   sqs          About an hour ago   Up About an hour             0.0.0.0:9324->9324/tcp, [::]:9324->9324/tcp

# then use part of the container name to exec into the container
$ docker compose exec localstack bash

# or use the full container name
$ docker exec -it billing-service-localstack-1 bash

# then inside the container, you can use aws cli to access the localstack services
root@7bf8044aa1e1:/opt/code/localstack# awslocal s3 ls
root@7bf8044aa1e1:/opt/code/localstack# awslocal sqs list-queues
```

- ### utilise stackup docker image to deploy cloudformation
- create a docker-compose.yml, if there is one and you want to use another one, create a yml file of any name, we will specify the file name in `docker compose` command.
- volumes include the folder that contains the cloudformation template files.
```yaml
version: '2.1'
services:
  stackup:
    image: realestate/stackup:1.7.0
    working_dir: /src
    volumes:
      - ./infrastructure:/src
      - ~/.aws:/root/.aws
    environment:
      - AWS_REGION
      - AWS_DEFAULT_REGION
      - AWS_PROFILE
```
- then run command to deploy the stack to aws cloudformation
```shell
$ docker-compose -f ./test.yml run --rm stackup norris-test up \
    -t cfn-template.yml \
    --preserve-template-formatting \
    --capability CAPABILITY_NAMED_IAM \
    --tags cfn-template.tags.yml \
    -p cfn-template.parameters.json \
    -o ReleaseVersion=1.1.1-602
# -f specifies the docker-compose yml file if it is not the default docker-compose.yml
# --rm or rm removes stopped service containers.
# norris-test is the stack name.
# -t template. Notice that the folder ./infrastructure is mounted as /src in the container, so no need to reference the read world location.
# --tags tags file in yml
# -p parameter json file
# -o pass in any parameters that is not included in the parameter json file
# --preserve-template-formatting: preserve the yaml version in the cfn template.
```
```yaml
# something.tags.yml
Something:Tag1: Value1
Something:Tag2: Value2
Something:Tag3: Value3
```
- template.parameters.json
```json
{
  "Env": "dev",
  "EcrRepository": "12345678.dkr.ecr.ap-southeast-2.amazonaws.com"
}
```

- ### Docker run stackup directly without a docker-compose.yml
```shell
$ REL_VER=1.1.1-602
$ STACK_NAME=norris-test
$ docker run --rm --init --volume $HOME/.aws:/root/.aws \
    --volume $(pwd)/infrastructure:/app \
    --workdir /app \
    --env AWS_PROFILE \
    --env AWS_REGION \
    --env AWS_DEFAULT_REGION \
    realestate/stackup:latest ${STACK_NAME} up \
      -t /app/${STACK_NAME}.yml \
      --preserve-template-formatting \
      -o ReleaseVersion=$REL_VER \
      --tags /app/${STACK_NAME}.tags.yml \
      --capability CAPABILITY_NAMED_IAM \
      --capability CAPABILITY_AUTO_EXPAND
```

- ### Docker login to aws ecr
```shell
$ aws ecr get-login-password | docker login --username AWS --password-stdin <AWS::AccountId>.dkr.ecr.us-west-2.amazonaws.com
```