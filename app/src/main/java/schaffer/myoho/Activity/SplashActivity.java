package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.Base.BaseAnimationListener;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.R;
import schaffer.myoho.Utils.SPUtils;

public class SplashActivity extends BaseActivity {

    private android.widget.RelativeLayout splashgroup;
    private TranslateAnimation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.splashgroup = (RelativeLayout) findViewById(R.id.splash_group);
        splashgroup.post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    private void init() {
        animation = new TranslateAnimation(0, 0, -windowHeight, 0);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        animation.setInterpolator(new BounceInterpolator());
        splashgroup.startAnimation(animation);
        animation.setAnimationListener(new BaseAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                //判断跳转条件
                if (SPUtils.get(MyApplication.IS_FIRST).equals("false")) {
                    //第一次跳转到引导页
                    Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                } else {
                    //之后就跳转到WelcomeActivity
                    SPUtils.save(MyApplication.IS_FIRST, "false");
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
}
