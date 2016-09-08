package schaffer.myoho.Base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import schaffer.myoho.UserConfig.User;
import schaffer.myoho.Utils.SPUtils;

/**
 * Created by a7352 on 2016/8/22.
 */
public class MyApplication extends Application {
    public static MyApplication app;
    public static final String IS_FIRST = "isFirst";
    public static boolean isLogin;
    public static User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化BmobSDK
        Bmob.initialize(this, "e3cd564325bb62a2adeee32d08976a82");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);
        app = this;
        //二维码zxing初始化?
        ZXingLibrary.initDisplayOpinion(this);
    }

    public boolean isLogin() {
        return user != null && SPUtils.getUserConfigValid();
    }


}
