package com.getlocal.plugins.caplss;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginResult;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorLottieSplashScreen")
public class CapacitorLottieSplashScreenPlugin extends Plugin {
    private final CapacitorLottieSplashScreen implementation = new CapacitorLottieSplashScreen();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    public void load() {
        boolean isEnabled = this.getConfig().getBoolean("Enabled", true);
        if (isEnabled) ShowLottieSplashScreenDialog();
        implementation.setAnimationEventListener(this::onAnimationEvent);
    }

    public void onAnimationEvent(String event) {
        bridge.triggerWindowJSEvent(event);
        notifyListeners(event, null);
    }

    private void ShowLottieSplashScreenDialog() {
        Context context = this.getContext();
        String lottiePath = this.getConfig().getString("LottieAnimationLocation");
        implementation.ShowLottieSplashScreenDialog(context, lottiePath);
    }

    @PluginMethod
    public void appLoaded(PluginCall call) {
        implementation.onAppLoaded();
        call.resolve();
    }

    @PluginMethod
    public void isAnimating(PluginCall call) {
        boolean isAnimating = implementation.isAnimating();
        JSObject ret = new JSObject();
        ret.put("isAnimating", isAnimating);
        call.resolve(ret);
    }
}
