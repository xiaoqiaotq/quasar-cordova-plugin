var exec = require('cordova/exec');
var channel = require('cordova/channel');

exports.coolMethod = function (arg0, success, error) {
  exec(success, error, 'wms', 'coolMethod', [arg0]);
};
exports.init = function () {
  channel.onCordovaReady.subscribe(function () {
    console.log("=======onCordovaReady===================")
    exec(message=>{
      n2js(message)
    }, error=>{alert(error)}, "wms", "coolMethod", []);
  });

};
