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
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorLottieSplashScreen")
public class CapacitorLottieSplashScreenPlugin extends Plugin {
    private CapacitorLottieSplashScreen implementation = new CapacitorLottieSplashScreen();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void load(){
        ShowLottieSplashScreenDialog();
    }

    public void ShowLottieSplashScreenDialog(){
        Context context = this.getContext();
        Dialog dialog = new Dialog(context, R.style.AppTheme_GetLocalLottieSplashScreen);
        dialog.setContentView(R.layout.activity_lottie_splash_screen);
        dialog.setCancelable(false);
        loadLottie(dialog);


        View decorView = dialog.getWindow().getDecorView();
        int uiOptions = decorView.getSystemUiVisibility();
        uiOptions = uiOptions | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        dialog.getWindow().setStatusBarColor(Color.TRANSPARENT);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog.show();
    }

    public void loadLottie(Dialog dialog){
        String lottiePath = this.getConfig().getString("LottieAnimationLocation");;
        LottieAnimationView lottieAnimationView = dialog.findViewById(R.id.animationView);
        lottieAnimationView.setAnimation(lottiePath);
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setSpeed(1F);
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    dialog.cancel();
                } catch(Exception ex) {
                    ex.toString();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

        });
    }
}
