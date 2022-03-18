package org.apache.cordova.wms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
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
  private static final String TAG = "WMS_PLUGIN";
  public static final String ACTION_DECODE = "android.intent.ACTION_DECODE_DATA";
  public static final String BARCODE_STRING_TAG = "barcode_string";
  public static final String BARCODE_TYPE_TAG = "barcodeType";
  public static final String BARCODE_LENGTH_TAG = "length";
  public static final String DECODE_DATA_TAG = "barcode";

  private BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      Log.i(TAG,"onReceive , action:" + action);
      // Get Scan Image . Make sure to make a request before getting a scanned image
      if (ACTION_DECODE.equals(action)) {
        // Get scan results, including string and byte data etc.
        byte[] barcode = intent.getByteArrayExtra(DECODE_DATA_TAG);
        int barcodeLen = intent.getIntExtra(BARCODE_LENGTH_TAG, 0);
        byte temp = intent.getByteExtra(BARCODE_TYPE_TAG, (byte) 0);
        String barcodeStr = intent.getStringExtra(BARCODE_STRING_TAG);
        Log.i(TAG,"barcode type:" + temp);
        String scanResult = new String(barcode, 0, barcodeLen);
        // print scan results.
        scanResult = " length：" + barcodeLen + "\nbarcode：" + scanResult  + "\nbarcodeStr:" + barcodeStr;

        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, barcodeStr);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
      }
    }
  };

  private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      this.callbackContext = callbackContext;
        if (action.equals("coolMethod")) {
 //           String message = args.getString(0);
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
//    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, callbackContext.toString()+" cordova: "+ UUID.randomUUID().toString());
 //   pluginResult.setKeepCallback(true);
 //   callbackContext.sendPluginResult(pluginResult);
  //  Toast.makeText(cordova.getActivity(), "你好 cordova 回来了 :"+callbackContext.toString(), Toast.LENGTH_SHORT).show();

    // register broadcast
    IntentFilter filter = new IntentFilter();
    filter.addAction(ACTION_DECODE);
    cordova.getContext().registerReceiver(mReceiver, filter);
  }

  @Override
  public void onPause(boolean multitasking) {
    super.onPause(multitasking);
    cordova.getContext().unregisterReceiver(mReceiver);

  }
}

