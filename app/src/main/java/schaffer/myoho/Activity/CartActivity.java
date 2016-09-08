package schaffer.myoho.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import schaffer.myoho.Event.DeleteAndPayEvent;
import schaffer.myoho.Event.EditCartGoodsEvent;
import schaffer.myoho.Fragment.FragmentCart;
import schaffer.myoho.R;

public class CartActivity extends AppCompatActivity {

    private android.widget.ImageView back;
    private android.widget.RelativeLayout activitycarttbgroup;
    private android.widget.FrameLayout frameLayout;
    private FragmentManager fm;
    private FragmentCart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        frameLayout = (FrameLayout) findViewById(R.id.activity_cart_fragment_group);
        fm = getSupportFragmentManager();
        cart = new FragmentCart();
        fm.beginTransaction().replace(R.id.activity_cart_fragment_group, cart).commit();
    }


    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.main_in, R.anim.cart_out);
        super.onBackPressed();
    }

    public void back(View view) {
        //返回按钮
        onBackPressed();
    }

    public void editCart(View view) {
        //设置当前list的状态
        TextView tv = (TextView) view;
        if (tv.getText().equals("编辑")) {
            EventBus.getDefault().post(new EditCartGoodsEvent(EditCartGoodsEvent.ENABLE));
            tv.setText("完成");
        } else {
            EventBus.getDefault().post(new EditCartGoodsEvent(EditCartGoodsEvent.DISABLE));
            tv.setText("编辑");
        }
    }

    public void pay(View view) {
        //结算按钮
        Button btn = (Button) view;
        String s = btn.getText().toString();
        if (s.equals("结算")) {
            EventBus.getDefault().post(new DeleteAndPayEvent(true));
        } else if (s.equals("删除")) {
            EventBus.getDefault().post(new DeleteAndPayEvent(false));
        }
    }

}
