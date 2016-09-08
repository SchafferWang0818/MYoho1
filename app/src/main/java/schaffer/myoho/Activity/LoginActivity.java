package schaffer.myoho.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.R;
import schaffer.myoho.UserConfig.User;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.SPUtils;

public class LoginActivity extends BaseActivity {

    private android.support.design.widget.TextInputEditText account;
    private android.support.design.widget.TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.password = (TextInputEditText) findViewById(R.id.login_pwd_edt);
        this.account = (TextInputEditText) findViewById(R.id.login_count_edt);
    }

    public void login(View view) {
        String pwd = password.getText().toString();
        String account = this.account.getText().toString();
        if (pwd == null || account == null) {
            MToast.notifys("好像缺点什么....");
            return;
        }
        if (pwd.equals("123456") && account.equals("123456")) {
            long time = System.currentTimeMillis();
            long loginValidTime = 30 * 60 * 1000;
//            int useId, String imgPath, String userName, String token, int lastLoginTime, int loginValidTime
            User user = new User(1, "http://f.hiphotos.baidu.com/image/pic/item/d50735fae6cd7b899b6bd2850d2442a7d9330eb4.jpg", "我想看看名字能有多长", "token", time, loginValidTime);
            MyApplication.user = user;
            SPUtils.setUserConfig(user);
            MLog.w("user信息保存成功");
            sendLoginMessageToNet();
            setResult(RESULT_OK);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
    }

    private void sendLoginMessageToNet() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }
}
