package schaffer.myoho.Utils;

import android.widget.Toast;

import schaffer.myoho.Base.MyApplication;

/**
 * Created by a7352 on 2016/8/25.
 */
public class MToast {
    public static void notifys(String content) {
        Toast.makeText(MyApplication.app, content, Toast.LENGTH_SHORT).show();
    }

}
