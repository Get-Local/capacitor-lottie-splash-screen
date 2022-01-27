package com.getlocal.plugins.caplss;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

public class CapacitorLottieSplashScreen {

    interface AnimationEventListener{
        void onAnimationEvent(String event);
    }

    @Nullable
    AnimationEventListener animationEventListener;
    private boolean isAppLoaded = false;
    private boolean isAnimationEnded = false;
    final public static String ON_ANIMATION_END = "onAnimationEnd";

    Dialog dialog = null;

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public void setAnimationEventListener(@Nullable AnimationEventListener animationEventListener) {
        this.animationEventListener = animationEventListener;
    }

    public void onAppLoaded() {
        isAppLoaded = true;
        if(isAnimationEnded){
            hideDialog();
        }
    }

    public boolean isAnimating() {
        return !isAnimationEnded;
    }

    public void ShowLottieSplashScreenDialog(Context context, String lottiePath) {
        dialog = new Dialog(context, R.style.AppTheme_GetLocalLottieSplashScreen);
        dialog.setContentView(R.layout.activity_lottie_splash_screen);
        dialog.setCancelable(false);
        loadLottie(dialog, lottiePath);

        View decorView = dialog.getWindow().getDecorView();
        int uiOptions = decorView.getSystemUiVisibility();
        uiOptions = uiOptions | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        dialog.getWindow().setStatusBarColor(Color.TRANSPARENT);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog.show();
    }

    private void loadLottie(Dialog dialog, String lottiePath){

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
                    isAnimationEnded = true;
                    if(isAppLoaded) hideDialog();
                    animationEventListener.onAnimationEvent(ON_ANIMATION_END);

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

    private void hideDialog(){
        dialog.cancel();
    }
}
