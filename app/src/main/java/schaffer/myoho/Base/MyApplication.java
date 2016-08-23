package schaffer.myoho.Base;

import android.app.Application;

/**
 * Created by a7352 on 2016/8/22.
 */
public class MyApplication extends Application {
    public static MyApplication app;
    public static final String IS_FIRST = "isFirst";
    public static boolean isLogin = true;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
