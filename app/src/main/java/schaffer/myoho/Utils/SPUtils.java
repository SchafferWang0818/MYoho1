package schaffer.myoho.Utils;

import android.content.Context;

import schaffer.myoho.Base.MyApplication;

/**
 * Created by a7352 on 2016/8/22.
 */
public class SPUtils {
    public static void save(String key, String value) {
        MyApplication.app.getSharedPreferences("config", Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static String get(String key) {

        return MyApplication.app.getSharedPreferences("config", Context.MODE_PRIVATE).getString(key, "");
    }

}
