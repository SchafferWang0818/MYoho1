package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.Bean.CartGoodsBean;
import schaffer.myoho.DefinedView.DRadioButton;
import schaffer.myoho.Event.UserStateEvent;
import schaffer.myoho.Fragment.FragmentCart1;
import schaffer.myoho.Fragment.FragmentCategory;
import schaffer.myoho.Fragment.FragmentHome;
import schaffer.myoho.Fragment.FragmentMine;
import schaffer.myoho.Fragment.FragmentRoam;
import schaffer.myoho.R;
import schaffer.myoho.UserConfig.User;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;
import schaffer.myoho.Utils.SPUtils;

/**
 * 首先将所有的Fragment加载出来
 * 然后将Fragment可以通过key找到对象
 * 给Button设置tag,与Fragment的key一一对应起来
 * 点击按钮的时候,通过key与当前的key进行判断,是当前的key就不用重新加载,否则就加载,并且替换当前的key
 */
public class MainActivity extends AppCompatActivity {


    ListView mainBottomLv;
    LinearLayout bottomView;
    RadioButton mainRbHomeome;
    RadioButton mainRbCategory;
    RadioButton mainRbRoam;
    DRadioButton mainRbCart;
    RadioButton mainRbMine;
    RadioGroup mainTopRbGroup;
    FrameLayout mainTopGroup;
    RelativeLayout topView;
    private FragmentHome home;
    private FragmentManager fragmentManager;
    private HashMap<String, BaseFragment> fragmentMap;
    private SlidingPaneLayout mainslide;
    private List<RadioButton> radioButtons;
    private List<CartGoodsBean.CartBean> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        boolean login = MyApplication.app.isLogin();

        initFragment();
        setRadioTag();
        mainRbHomeome.performClick();
        if (login) {
            //网络请求数据
            new HttpUtils().loadData(PathUtils.JSON_CART_LIST_HEAD, PathUtils.JSON_CART_LIST_BODY).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
                @Override
                public void loadSuccess(String content) {
//                    JsonPrintAllUtils.printJson(content);
                    CartGoodsBean cartGoodsBean = new Gson().fromJson(content, CartGoodsBean.class);
                    cart = cartGoodsBean.getCart();
                    mainRbCart.post(new Runnable() {
                        @Override
                        public void run() {
                            mainRbCart.setShow(true, cart.size());
                        }
                    });
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            MLog.w("" + Thread.currentThread().getName());
                            User userConfig = SPUtils.getUserConfig();
                            MyApplication.user = userConfig;
                            EventBus.getDefault().post(new UserStateEvent());
                        }
                    }.start();
                }

                @Override
                public void loadFailed(String errorMsg) {
                    mainRbCart.setShow(false, 0);
                }
            });
        } else {
            //从本地得到sp,根据得到的list的内容得到数量
            int cart_num = SPUtils.getLocalCartGoodsNum();
            mainRbCart.setShow(true, cart_num);
        }
    }

    private void setRadioTag() {
        mainRbHomeome.setTag(FragmentHome.class.getSimpleName());
        mainRbCategory.setTag(FragmentCategory.class.getSimpleName());
        mainRbRoam.setTag(FragmentRoam.class.getSimpleName());
        mainRbCart.setTag(FragmentCart1.class.getSimpleName());
        mainRbMine.setTag(FragmentMine.class.getSimpleName());
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentMap = new HashMap<>();
        fragmentMap.put(FragmentHome.class.getSimpleName(), new FragmentHome());
        fragmentMap.put(FragmentCategory.class.getSimpleName(), new FragmentCategory());
        fragmentMap.put(FragmentRoam.class.getSimpleName(), new FragmentRoam());
        fragmentMap.put(FragmentCart1.class.getSimpleName(), new FragmentCart1());
        fragmentMap.put(FragmentMine.class.getSimpleName(), new FragmentMine());
    }

    private void initViews() {
        mainslide = (SlidingPaneLayout) findViewById(R.id.main_slide);
        mainTopGroup = (FrameLayout) findViewById(R.id.main_top_group);
        mainTopRbGroup = (RadioGroup) findViewById(R.id.main_top_rb_group);
        topView = (RelativeLayout) findViewById(R.id.topView);
        bottomView = (LinearLayout) findViewById(R.id.bottomView);
        mainRbHomeome = (RadioButton) findViewById(R.id.main_rb_homeome);
        mainRbCategory = (RadioButton) findViewById(R.id.main_rb_category);
        mainRbRoam = (RadioButton) findViewById(R.id.main_rb_roam);
        mainRbCart = (DRadioButton) findViewById(R.id.main_rb_cart);
        mainRbMine = (RadioButton) findViewById(R.id.main_rb_mine);
        mainBottomLv = (ListView) findViewById(R.id.main_bottom_lv);
        radioButtons = new ArrayList<>();
        radioButtons.add(mainRbHomeome);
        radioButtons.add(mainRbCategory);
        radioButtons.add(mainRbRoam);
        radioButtons.add(mainRbCart);
        radioButtons.add(mainRbMine);
    }


    public void bottomItemClick(View view) {
        switch (view.getId()) {
            case R.id.main_rb_homeome:

                dumpToFragment((String) mainRbHomeome.getTag());
                break;
            case R.id.main_rb_category:
                fragmentMap.put(FragmentCategory.class.getSimpleName(), new FragmentCategory());
                dumpToFragment((String) mainRbCategory.getTag());
                break;
            case R.id.main_rb_roam:
                dumpToFragment((String) mainRbRoam.getTag());
                break;
            case R.id.main_rb_cart:
                if (!MyApplication.app.isLogin()) {
                    //切换Fragment
                    dumpToFragment((String) mainRbCart.getTag());
                } else {
                    //切换Activity
                    intentToCartActivity();
                }
                break;
            case R.id.main_rb_mine:
                dumpToFragment((String) mainRbMine.getTag());
                break;
        }
    }

    public void openSliding(View v) {
        if (!mainslide.isOpen()) {
            mainslide.openPane();
        } else {
            mainslide.closePane();
        }
    }

    String currentTag = FragmentHome.class.getSimpleName();
    String fragmentTag = "";
    String lastTag = "";

    /**
     * 点击按钮,传入Tag,当前显示Fragment的Tag和传入的Tag相同时说明多次点击同一个按钮,不予加载
     * 否则得到按钮对应的Fragment进行替换
     *
     * @param tag
     */
    public void dumpToFragment(String tag) {
        BaseFragment fragment = fragmentMap.get(tag);
        Log.w("TAG", "tag:" + tag + ",fragmentTag:" + fragmentTag + ",currentTag:" + currentTag);
        if (!tag.equals(fragmentTag)) {
            lastTag = currentTag;
            fragmentManager.beginTransaction().replace(R.id.main_top_group, fragment).commit();
            fragmentTag = tag;
        }
        currentTag = tag;
    }

    public void intentToCartActivity() {
        overridePendingTransition(R.anim.cart_in, R.anim.main_out);
        startActivity(new Intent(this, CartActivity.class));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (RadioButton radioButton : radioButtons) {
                    String str = (String) radioButton.getTag();
                    if (currentTag.equals(str)) {
                        radioButton.performClick();
                        return;
                    }
                }
            }
        }, 300);
    }

    static Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.choose_in, R.anim.main_out);
        super.onBackPressed();

    }

    public void codeScan(View view) {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                MToast.notifys("解析结果-->" + result);
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                MToast.notifys("解析失败");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
