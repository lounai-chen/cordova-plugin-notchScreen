var exec = require('cordova/exec');

var notchScreen = {
    //判断是否有刘海屏
    isNotchScreen:function(success,error){
        exec(success, error, 'notchScreen', 'isNotchScreen', []);
    },
    //获取刘海屏的参数
    getNotchParams:function(success,error){
        exec(success, error, 'notchScreen', 'getNotchParams', []);
    }
}

module.exports = notchScreen