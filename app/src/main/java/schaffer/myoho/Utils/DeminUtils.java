package schaffer.myoho.Utils;

import schaffer.myoho.Base.MyApplication;

/**
 * Created by a7352 on 2016/8/22.
 */
public class DeminUtils {
    public static int dp2px(int dpValue) {
        return (int) (MyApplication.app.getResources().getDisplayMetrics().density * dpValue + 0.5f);
    }
}
