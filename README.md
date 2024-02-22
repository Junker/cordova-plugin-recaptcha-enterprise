# Cordova Recaptcha Enterprise Plugin

This is a cordova plugin for Google reCAPTCHA Ente
rprise.

## Installation
```
cordova plugin add https://github.com/Junker/cordova-plugin-recaptcha-enterprise.git --save
```

## Usage

```javascript
window.plugins.recaptcha.initialize('key', 10000, 
                                    () => console.log('reCAPTCHA initialized!'),
                                    err => console.error(err));

window.plugins.recaptcha.execSignup(10000, 
                                    token => console.log('Token received:'+token), 
                                    err => console.error(err));

```

## API

- `initialize(key, timeout, success_callback, failure_callback)`
- `execLogin(timeout, success_callback, failure_callback)`: Call action in Login workflow
- `execSignup(timeout, success_callback, failure_callback)`: Call action in Sign-up workflow 
- `execCustom(action_name, timeout, success_callback, failure_callback)`: Call action in custom workflow

Args:
- `timeout` - amount of time in milliseconds.

## Caveats

- It only supports Android for now


## Documentation
- https://cloud.google.com/recaptcha-enterprise/docs/instrument-android-apps#native-android
- https://cloud.google.com/recaptcha-enterprise/docs/reference/android/com/google/android/recaptcha/RecaptchaAction
