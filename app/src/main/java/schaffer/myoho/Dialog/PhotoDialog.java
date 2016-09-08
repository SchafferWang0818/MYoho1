package schaffer.myoho.Dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import schaffer.myoho.Event.PhotosEvent;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;

/**
 * Created by a7352 on 2016/9/8.
 */
public class PhotoDialog extends BaseDialog {


    private android.widget.Button photos;
    private android.widget.Button camera;
    private android.widget.Button cancel;

    public PhotoDialog(Context context) {

        super(context);
        window.setContentView(R.layout.dialog_get_photo);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.style_dialog_add);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(windowWidth, DeminUtils.dp2px(210));
        decorView.setPadding(0, 0, 0, 0);
        this.cancel = (Button) findViewById(R.id.photo_cancel_btn);
        this.camera = (Button) findViewById(R.id.photo_btn2);
        this.photos = (Button) findViewById(R.id.photo_btn1);
        initListener();
    }

    private void initListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从摄像头选取
                EventBus.getDefault().post(new PhotosEvent(2));
                dismiss();
            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册选取
                EventBus.getDefault().post(new PhotosEvent(1));
                dismiss();
            }
        });
    }
}
