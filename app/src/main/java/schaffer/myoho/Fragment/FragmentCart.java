package schaffer.myoho.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Activity.LoginActivity;
import schaffer.myoho.Adapter.CartGoodsAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.Bean.CartGoodsBean;
import schaffer.myoho.Event.AllCheckEvent;
import schaffer.myoho.Event.DeleteAndPayEvent;
import schaffer.myoho.Event.EditCartGoodsEvent;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentCart extends BaseFragment {


    private android.widget.Button payBtn;
    private android.widget.TextView moneyNumTv;
    private android.widget.TextView freightTv;
    private android.widget.CheckBox checkAllCb;
    private android.widget.RelativeLayout fragmentcartlistgroup;
    private android.widget.ImageButton activitycartback;
    private android.widget.RelativeLayout activitycarttbgroup;
    private android.widget.ListView lv;
    private Button careBtn;
    private List<CartGoodsBean.CartBean> cart;
    private CartGoodsAdapter adapter;
    private ImageButton back;

    @Override
    protected void initDatas() {
        if (MyApplication.app.isLogin()) {

            back.setVisibility(View.VISIBLE);
            PathUtils.JSON_CART_LIST_USER_ID = MyApplication.user.getUseId();
            new HttpUtils().loadData(PathUtils.JSON_CART_LIST_HEAD, PathUtils.JSON_CART_LIST_BODY).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {

                @Override
                public void loadSuccess(String content) {
//                    JsonPrintAllUtils.printJson(content);
                    CartGoodsBean cartGoodsBean = new Gson().fromJson(content, CartGoodsBean.class);
                    cart = cartGoodsBean.getCart();
                    for (CartGoodsBean.CartBean cartBean : cart) {
                        cartBean.type = CartGoodsAdapter.TYPE_DISABLE;
                        cartBean.checked = true;
                    }
                    adapter = new CartGoodsAdapter(cart, ac);
                    lv.setAdapter(adapter);
                    checkAllCb.setChecked(true);
                }

                @Override
                public void loadFailed(String errorMsg) {
                    MLog.w("购物车列表数据打印出错-->" + errorMsg);
                }
            });
        } else {
            //从本地读取购物车信息

        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        checkAllCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MLog.w("全选按钮当前状态-->" + checkAllCb.isChecked());
//                checkAllCb.setChecked(!checkAllCb.isChecked());
//                for (CartGoodsBean.CartBean cartBean : cart) {
//                    cartBean.setChecked(!checkAllCb.isChecked());
//                    MLog.w("子check-->" + checkAllCb.isChecked());
//                }
//                MLog.w("全选按钮当前状态-->" + checkAllCb.isChecked());
//                adapter.notifyDataSetChanged();
                if (!checkAllCb.isChecked()) {
                    //说明已经全选,将全部置为不选择
//                    checkAllCb.setChecked(true);
                    for (int i = 0; i < cart.size(); i++) {
                        cart.get(i).setChecked(false);
                    }
                } else {
                    //说明要置为全选
                    for (int i = 0; i < cart.size(); i++) {
                        if (!cart.get(i).isChecked()) {
                            cart.get(i).setChecked(true);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
//        checkAllCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                MLog.w("onCheckedChanged全选按钮当前状态-->" + isChecked);
//                for (CartGoodsBean.CartBean cartBean : cart) {
//                    cartBean.setChecked(isChecked);
//                    MLog.w("子check-->" + isChecked);
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
        careBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.app.isLogin()) {
                    //登录,关注成功
                    for (CartGoodsBean.CartBean cartBean : cart) {
                        boolean checked = cartBean.isChecked();
                        if (checked) {
                            MyApplication.user.careList.add(cartBean);
                        }
                    }
                    MToast.notifys("关注成功,需要发送数据,并且删除该商品");
                } else {
                    //未登录
                    Intent intent = new Intent(ac, LoginActivity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        EventBus.getDefault().register(this);
        back = (ImageButton) view.findViewById(R.id.activity_cart_back);
        this.lv = (ListView) view.findViewById(R.id.fragment_cart_list_lv);
        this.checkAllCb = (CheckBox) view.findViewById(R.id.fragment_cart_list_check_all);
        this.payBtn = (Button) view.findViewById(R.id.fragment_cart_pay_btn);

        this.freightTv = (TextView) view.findViewById(R.id.fragment_cart_freight_tv);
        this.moneyNumTv = (TextView) view.findViewById(R.id.fragment_cart_money_num_tv);
        this.careBtn = (Button) view.findViewById(R.id.fragment_cart_care_btn);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCartGoodsList(EditCartGoodsEvent event) {
        if (event.type == EditCartGoodsEvent.ENABLE) {
            //要求可编辑状态
            changeListState(CartGoodsAdapter.TYPE_ENABLE);
        } else {
            //要求不可编辑状态
            changeListState(CartGoodsAdapter.TYPE_DISABLE);
        }
    }

    private void changeListState(int type) {
        if (cart != null) {
            for (CartGoodsBean.CartBean cartBean : cart) {
                cartBean.type = type;
            }
            adapter.notifyDataSetChanged();
        }
        if (type == EditCartGoodsEvent.ENABLE) {
            payBtn.setText("删除");
            freightTv.setVisibility(View.GONE);
            moneyNumTv.setVisibility(View.GONE);
            careBtn.setVisibility(View.VISIBLE);
        } else if (type == EditCartGoodsEvent.DISABLE) {
            payBtn.setText("结算");
            freightTv.setVisibility(View.VISIBLE);
            moneyNumTv.setVisibility(View.VISIBLE);
            careBtn.setVisibility(View.GONE);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCheckAll(AllCheckEvent event) {
//        boolean checked = event.checked;
//        checkAllCb.setChecked(false);
//        if (checked){
//            checkAllCb.setChecked(true);
//        }else{
////            List<Integer> checks = event.checks;
////            for (int i = 0; i < cart.size(); i++) {
////                for (int i1 = 0; i1 < checks.size(); i1++) {
////                    if (i==checks.get(i1)){
////                        cart.get(i).setChecked(true);
////                    }
////                }
////            }
//
//            adapter.notifyDataSetChanged();
//        }
        if (!event.checked) {
            if (checkAllCb.isChecked()) {
                checkAllCb.setChecked(false);
            }
        } else {
            if (checkAllCb.isChecked()) {
                return;
            }
            checkAllCb.setChecked(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteOrPay(DeleteAndPayEvent event) {
        if (event.state) {
            //结算


        } else {
            //删除--->存在问题
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < cart.size(); i++) {
                boolean checked = cart.get(i).isChecked();
                if (checked) {
                    integers.add(i);
                }
            }
            for (int i = 0; i < cart.size(); i++) {
                for (int size = integers.size() - 1; size >= 0; size--) {
                    if (i == integers.get(size)) {
                        cart.remove(i);
                    }
                }
            }
            adapter.notifyDataSetChanged();
            MLog.w("delete finish.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED) {
                MToast.notifys("已取消登录");
                return;
            }
            if (resultCode == Activity.RESULT_OK) {
                //重新加载购物车的信息
                if (cart.size() > 0)
                    for (CartGoodsBean.CartBean cartBean : cart) {
                        boolean checked = cartBean.isChecked();
                        if (checked) {
                            MyApplication.user.careList.add(cartBean);
                        }
                    }
                MToast.notifys("此处应该从服务器加载数据,重新获取数据源");
            }
        }
    }



}
