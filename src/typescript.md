- ### Start a TypeScript project
```shell
# make sure node and npm are installed
$ node -v
$ npm -v

# create a new project folder
$ mkdir my-typescript-project
$ cd my-typescript-project

# initialize a new npm project
# -y flag uses default settings (answer 'yes' to all prompts)
$ npm init -y

# install TypeScript as a development dependency
$ npm install typescript --save-dev

# create a tsconfig.json file with default settings
$ npx tsc --init

# create a src folder and a sample TypeScript file
$ mkdir src
$ echo "const greeting: string = 'Hello, TypeScript!'; console.log(greeting);" > src/index.ts

# compile the TypeScript file to JavaScript
$ npx tsc

# run the compiled JavaScript file
$ node dist/index.js

# optionally, install ts-node to run TypeScript files directly
$ npm install ts-node --save-dev
$ npx ts-node src/index.ts

# optionally, add scripts to package.json for easier commands
# Open package.json and add the following under "scripts":
# "scripts": {
#   "build": "tsc",
#   "start": "node dist/index.js",
#   "dev": "ts-node src/index.ts"
# }



```