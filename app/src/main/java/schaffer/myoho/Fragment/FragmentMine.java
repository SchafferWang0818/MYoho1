package schaffer.myoho.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import me.nereo.imagechoose.MultiImageSelectorActivity;
import schaffer.myoho.Activity.LoginActivity;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.Dialog.PhotoDialog;
import schaffer.myoho.Event.PhotosEvent;
import schaffer.myoho.Event.UserStateEvent;
import schaffer.myoho.R;
import schaffer.myoho.UserConfig.User;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentMine extends BaseFragment {


    private android.widget.Button loginBtn;
    private android.widget.ImageView headIv;
    private android.widget.TextView nameTv;
    private android.widget.LinearLayout group;
    private android.widget.ViewFlipper flipper;
    public static final int REQUEST_IMAGE = 8;

    @Override
    protected void initDatas() {

    }

    private void inflateUserInfo() {
        loadUserInfo();
    }

    @Override
    protected void initListener() {
        super.initListener();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ac, LoginActivity.class);
                startActivityForResult(intent, 5);
            }
        });
        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册相机获取图片dialog
                PhotoDialog photoDialog = new PhotoDialog(ac);
                photoDialog.show();
            }
        });
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        EventBus.getDefault().register(this);
        this.flipper = (ViewFlipper) view.findViewById(R.id.mine_flipper);
        this.group = (LinearLayout) view.findViewById(R.id.mine_user_head_name_group);
        this.nameTv = (TextView) view.findViewById(R.id.mine_user_name_tv);
        this.headIv = (ImageView) view.findViewById(R.id.mine_user_head_iv);
        this.loginBtn = (Button) view.findViewById(R.id.mine_login_btn);
        flipper.setAutoStart(true);
        flipper.setInAnimation(AnimationUtils.loadAnimation(ac, R.anim.mine_flipper_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(ac, R.anim.mine_flipper_out));
        if (MyApplication.app.isLogin()) {
            //登录的情况下
            group.post(new Runnable() {
                @Override
                public void run() {
                    group.setVisibility(View.VISIBLE);
                    loginBtn.setVisibility(View.GONE);

                    inflateUserInfo();
                }
            });
        } else {
            group.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == 5) {
            //说明登录成功
            loadUserInfo();
        }
        if (requestCode == 8) {
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = 5;
            options.outWidth = DeminUtils.dp2px(60);
            options.outHeight = DeminUtils.dp2px(60);
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path.get(0), options);
            headIv.setImageBitmap(bitmap);
            //发送到服务器
        }

    }

    private void loadUserInfo() {
        User user = MyApplication.user;
        String imgPath = user.getImgPath();
        String userName = user.getUserName();
        loginBtn.setVisibility(View.GONE);
        group.setVisibility(View.VISIBLE);
        nameTv.setText(userName);
        Picasso.with(ac).load(imgPath).placeholder(R.drawable.mine_default_head).into(headIv);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userStateEvent(UserStateEvent event) {
        MLog.w("MainActivity-->Mine-->userState");
        setGroupVisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        MLog.w("setUserVisibleHint"+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setGroupVisible();
        }
    }

    private void setGroupVisible() {
        if (MyApplication.app.isLogin()) {
            //登录的情况下
            group.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
            inflateUserInfo();
            String imgPath = MyApplication.user.getImgPath();
            String userName = MyApplication.user.getUserName();
            Picasso.with(ac).load(imgPath).into(headIv);
            nameTv.setText(userName);
        } else {
            group.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void choosePhotoRoad(PhotosEvent event) {
        if (event.type == 1) {
            //相册选取
            MLog.w("相册选取");
            Intent intent = new Intent(ac, MultiImageSelectorActivity.class);
            // 是否显示调用相机拍照
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
            // 最大图片选择数量
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
            // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
            startActivityForResult(intent, REQUEST_IMAGE);
        } else {
            //拍照获得
            MToast.notifys("暂时使用相册的照相机吧...");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
