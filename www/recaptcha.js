var recaptcha = {
    initialize: function(key, timeout, callback, fail) {
        if (!cordova || !cordova.exec) {
            fail('Cannot find Cordova');
            return;
        }

        cordova.exec(
            function(result) {
                if (typeof callback === 'function') {
                    callback(result);
                }
            },
            function(error) {
                if (typeof fail === 'function') {
                    fail(error);
                }
            },
            'Recaptcha', 'initialize', [key, timeout]
        );
    },

    execLogin: function(timeout, callback, fail) {
        if (!cordova || !cordova.exec) {
            fail('Cannot find Cordova');
            return;
        }

        cordova.exec(
            function(result) {
                if (typeof callback === 'function') {
                    callback(result);
                }
            },
            function(error) {
                if (typeof fail === 'function') {
                    fail(error);
                }
            },
            'Recaptcha', 'execLogin', [timeout]
        );
    },


    execSignup: function(timeout, callback, fail) {
        if (!cordova || !cordova.exec) {
            fail('Cannot find Cordova');
            return;
        }

        cordova.exec(
            function(result) {
                if (typeof callback === 'function') {
                    callback(result);
                }
            },
            function(error) {
                if (typeof fail === 'function') {
                    fail(error);
                }
            },
            'Recaptcha', 'execSignup', [timeout]
        );
    },
    execCustom: function(action, timeout, callback, fail) {
        if (!cordova || !cordova.exec) {
            fail('Cannot find Cordova');
            return;
        }

        cordova.exec(
            function(result) {
                if (typeof callback === 'function') {
                    callback(result);
                }
            },
            function(error) {
                if (typeof fail === 'function') {
                    fail(error);
                }
            },
            'Recaptcha', 'execCustom', [action, timeout]
        );
    },
};

module.exports = recaptcha;
