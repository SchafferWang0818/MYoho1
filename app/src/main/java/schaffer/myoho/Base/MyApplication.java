package schaffer.myoho.Base;

import android.app.Application;

import schaffer.myoho.Bean.UserBean;

/**
 * Created by a7352 on 2016/8/22.
 */
public class MyApplication extends Application {
    public static MyApplication app;
    public static final String IS_FIRST = "isFirst";
    public static boolean isLogin = true;
    public static UserBean user = new UserBean(0, "path", "name");

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
