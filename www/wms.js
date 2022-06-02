var exec = require('cordova/exec');
var channel = require('cordova/channel');

exports.coolMethod = function (arg0, success, error) {
  exec(success, error, 'wms', 'coolMethod', [arg0]);
};
exports.beep = function (arg0,arg1,arg2, success, error) {
  exec(success, error, 'wms', 'beep', [arg0,arg1,arg2]);
};
exports.init = function () {
  channel.onCordovaReady.subscribe(function () {
    console.log("=======onCordovaReady===================")
    exec(message=>{
      n2js(message)
    }, error=>{alert(error)}, "wms", "coolMethod", []);
  });

};
