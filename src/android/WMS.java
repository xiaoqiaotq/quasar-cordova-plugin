package org.apache.cordova.wms;

import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * This class echoes a string called from JavaScript.
 */
public class WMS extends CordovaPlugin {

  private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      this.callbackContext = callbackContext;
        if (action.equals("coolMethod")) {
//             String message = args.getString(0);
//            this.coolMethod(message, callbackContext);
          Toast.makeText(cordova.getActivity(), callbackContext.toString(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

  @Override
  public void onResume(boolean multitasking) {
    super.onResume(multitasking);
    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, "cordova: "+ UUID.randomUUID().toString());
    pluginResult.setKeepCallback(true);
    callbackContext.sendPluginResult(pluginResult);
//     Toast.makeText(cordova.getActivity(), "你好 cordova 回来了 :"+callbackContext.toString(), Toast.LENGTH_SHORT).show();

  }
}

