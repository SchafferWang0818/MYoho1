package schaffer.myoho.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by a7352 on 2016/9/1.
 */
public class BaseDialog extends Dialog {

    public final Activity a;
    public Window window;
    public View decorView;
    public final int windowWidth;
    public final int windowHeight;
    public WindowManager.LayoutParams attributes;

    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        window.requestFeature(Window.FEATURE_NO_TITLE);
        a = (Activity) context;
        window = getWindow();
        decorView = window.getDecorView();
        attributes = window.getAttributes();
        windowWidth = a.getWindowManager().getDefaultDisplay().getWidth();
        windowHeight = a.getWindowManager().getDefaultDisplay().getHeight();
    }

}

