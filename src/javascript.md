- ### node version manager switch versions
```shell
$ nvm ls
$ nvm use 16.14.0
```
- ### list remote available versions
```shell
$ nvm ls-remote --lts
$ nvm version-remote --lts
```
- ### install a version
```shell
$ nvm install node # "node" is an alias for the latest version
$ nvm install 14.7.0
```
- ### Run test without coverage
```shell
$ npm test -- -t="test name pattern regex" --coverage=false -u
$ # npm test -- = jest with -options
$ # -t = --testNamePattern
$ # -u = --updateSnapshot
$ # --coverage = --collectCoverage
$ npm test -- the-js-file-to-test.js -t="test name pattern regex" --coverage=false -u
```

- ### Set test timezone
put `process.env.TZ` in `jest.config.js` before `module.exports`
```js
process.env.TZ = 'UTC'
module.exports = {
    ...
}
```

- ### run cypress tests
```shell
$ node_modles/.bin/cypress open
$ # or
$ npm run test:functional:watch
```
```json
{
  "scripts": {
    "test:functional:watch": "cypress open"
  }
}
```

- ### npm ci vs npm install
```shell
# npm i stands for npm install
# npm ci stands for clean install
# npm install installs packages based on package.json
# npm ci installs packages with exact versions in package-lock.json
# npm install is used to install a dependency.
# npm ci is usually used to compile a project cloned from remote which does not have a node_module yet.
```

- ### npm version is separate from node version.
Check what packages are installed
```shell
$ npm list -g | grep aws-cdk
$ npm list | grep typescript
```