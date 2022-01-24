package com.getlocal.plugins.caplss;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
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
        dialog.show();
    }

    public void loadLottie(Dialog dialog){
        String lottiePath = this.getConfig().getString("LottieAnimationLocation");;
        LottieAnimationView lottieAnimationView = dialog.findViewById(R.id.animationView);
        lottieAnimationView.setAnimation(lottiePath);
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setSpeed(0.5F);
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
