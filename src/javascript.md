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