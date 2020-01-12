package com.zx.cap.plugindemo;

import android.content.Intent;


import com.example.activitytest.FirstActivity;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;




@NativePlugin()
public class ZxCapDemoPlugin extends Plugin {

    @PluginMethod()
    public void echo(PluginCall call) {
        String value = call.getString("value");


        System.out.println("----------------========++++========----------------");




//        Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent intent = new Intent(getActivity(), FirstActivity.class);
        getActivity().startActivity(intent);




        System.out.println("----------------========****========----------------");


        JSObject ret = new JSObject();
        ret.put("value1", value+value+value);
//        call.success(ret);
        call.resolve(ret);
    }
}
