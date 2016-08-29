package schaffer.myoho.Activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import schaffer.myoho.Base.BaseAnimationListener;
import schaffer.myoho.Base.BaseAnimatorListener;
import schaffer.myoho.R;

public class WelcomeActivity extends AppCompatActivity {

    private android.widget.ImageView welcomeiv;
    private android.widget.ImageView welcomecurtain;
    private ValueAnimator animator;
    private AlphaAnimation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.welcomecurtain = (ImageView) findViewById(R.id.welcome_curtain);
        this.welcomeiv = (ImageView) findViewById(R.id.welcome_iv);
        welcomeiv.setScaleX(1.5f);
        welcomeiv.setScaleY(1.5f);
        welcomecurtain.post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });

    }

    /**
     * 设置动画,先设置透明度补间动画   然后设置缩放属性动画
     */
    private void init() {
        alpha = new AlphaAnimation(1, 0.5f);
        alpha.setFillAfter(true);
        alpha.setDuration(1500);
        animator = ValueAnimator.ofFloat(1.5f, 1);
        animator.setDuration(1500);
        alpha.setAnimationListener(new BaseAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                welcomecurtain.clearAnimation();
                welcomecurtain.setVisibility(View.GONE);
                animator.start();
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                welcomeiv.setScaleX(value);
                welcomeiv.setScaleY(value);
            }
        });
        animator.addListener(new BaseAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dump(null);
                    }
                }, 1500);
            }
        });
        welcomecurtain.startAnimation(alpha);
    }

    public void dump(View view) {
        welcomecurtain.clearAnimation();
        alpha.cancel();
        animator.cancel();
        animator.removeAllUpdateListeners();
        Intent intent = new Intent(WelcomeActivity.this, ChooseActivity.class);
        startActivity(intent);
        finish();
    }
}
