package com.zx.cap.plugindemo;

import android.content.Intent;


import com.example.activitytest.FirstActivity;
import com.example.activitytest.SecondActivity;
import com.example.activitytest.ThirdActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;



// ## demo for 1) Return function data; 2) open native activities
//@NativePlugin()
//public class ZxCapDemoPlugin extends Plugin {
//
//    @PluginMethod()
//    public void echo(PluginCall call) {
//        String value = call.getString("value");
//
//
//        System.out.println("----------------========++++========----------------");
//
//
//
//
////        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Intent intent = new Intent(getActivity(), FirstActivity.class);
//        getActivity().startActivity(intent);
//
//
//
//
//        System.out.println("----------------========****========----------------");
//
//
//        JSObject ret = new JSObject();
//        ret.put("value1", value+value+value);
////        call.success(ret);
//        call.resolve(ret);
//    }
//}


// ## demo for 1) intent with result data returned, 2) in the SecondActivity open ACTION_VIEW for webview/browser.
@NativePlugin(
        requestCodes={ZxCapDemoPlugin.REQUEST_IMAGE_PICK} // register request code(s) for intent results
)
public class ZxCapDemoPlugin extends Plugin {
    protected static final int REQUEST_IMAGE_PICK = 12345; // Unique request code

    @PluginMethod()
    public void echo(PluginCall call) {
        saveCall(call);

//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");

        Intent intent = new Intent(this.getActivity(), SecondActivity.class);
        intent.putExtra("extra_data", "aaaaaaaaaaaa");

//        getActivity().startActivity(intent);
        startActivityForResult(call, intent, REQUEST_IMAGE_PICK);
    }

    // in order to handle the intents result, you have to @Override handleOnActivityResult
    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        // Get the previously saved call
        PluginCall savedCall = getSavedCall();

        if (savedCall == null) {
            return;
        }
        if (requestCode == REQUEST_IMAGE_PICK) {
            // Do something with the data
            System.out.println("----------------========****========----------------");
            System.out.println(data.getStringExtra("extra_data"));
        }
    }
}