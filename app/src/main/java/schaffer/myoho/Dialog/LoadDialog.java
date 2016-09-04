package schaffer.myoho.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;

/**
 * Created by a7352 on 2016/9/1.
 */
public class LoadDialog extends BaseDialog {
    public LoadDialog(Context context) {
        super(context);
        View view = View.inflate(a, R.layout.dialog_load, null);
        //亮度
        attributes.dimAmount = 0.4f;
        attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        setContentView(view);
        decorView.setBackgroundColor(Color.WHITE);
        decorView.setPadding(0, 0, 0, 0);
        window.setLayout(DeminUtils.dp2px(300), DeminUtils.dp2px(60));
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.style_dialog_add);
        setCanceledOnTouchOutside(false);
    }
}
