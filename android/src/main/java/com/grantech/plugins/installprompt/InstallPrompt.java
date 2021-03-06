package com.grantech.plugins.installprompt;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.util.Log;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;

import com.google.android.gms.instantapps.InstantApps;

import io.flutter.embedding.android.FlutterActivity;

/**
 * InstallPrompt
 */
public class InstallPrompt implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private FlutterActivity activity;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "installprompt");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        //here we have access to activity
        activity = (FlutterActivity) binding.getActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {}
    @Override
    public void onDetachedFromActivity() {}
    @Override
    public void onDetachedFromActivityForConfigChanges() {}

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("showInstallPrompt")) {
            String referrer = call.argument("referrer");
            String packageName = activity.getApplicationContext().getPackageName();
            Log.d("DART/NATIVE", "referrer " + referrer + " packageName " + packageName);
            Intent postInstallIntent = new Intent(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_DEFAULT)
                    .setPackage(packageName);

            // Prompt for pre-registration
            InstantApps.showInstallPrompt(activity, postInstallIntent, 7, referrer);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
