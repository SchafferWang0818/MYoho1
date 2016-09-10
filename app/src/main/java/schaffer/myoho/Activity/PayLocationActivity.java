package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import schaffer.myoho.Adapter.BaseListAdapter;
import schaffer.myoho.Adapter.PayLocationExAdapter;
import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.Bean.CartGoodsBean;
import schaffer.myoho.Bean.ExpandDataBean;
import schaffer.myoho.Bean.ExpandDataChildBean;
import schaffer.myoho.Bean.SerializableBean;
import schaffer.myoho.DefinedView.DExpandableListView;
import schaffer.myoho.R;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

public class PayLocationActivity extends BaseActivity {

    private android.widget.ListView lv;
    private List<CartGoodsBean.CartBean> cartList;
    private List<ExpandDataBean> exList;
    private DExpandableListView exLv;
    private PayLocationExAdapter exAdapter;
    private TextView locationTv;
    private View view;
    private MyAdapter lvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_location);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initListener() {
        locationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //定位服务
            }
        });
    }

    private void initAdapter() {
        lvAdapter = new MyAdapter(cartList);
        lv.setAdapter(lvAdapter);
    }

    private void initView() {
        this.lv = (ListView) findViewById(R.id.activity_pay_location_goods_lv);
        initHead();

    }

    private void initHead() {
        view = View.inflate(this, R.layout.item_head_lv_location, null);
        locationTv = (TextView) view.findViewById(R.id.item_head_lv_location_tv);
        exLv = new DExpandableListView(this);
        initExlvData();
        exAdapter = new PayLocationExAdapter(exList, this);
        exLv.setAdapter(exAdapter);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        exLv.setLayoutParams(layoutParams);
        lv.addHeaderView(view);
        lv.addHeaderView(exLv);
    }

    private void initExlvData() {
        exList = new ArrayList<>();

        List<ExpandDataChildBean> childBeen1 = new ArrayList<>();
        childBeen1.add(new ExpandDataChildBean("在线支付", true));
        childBeen1.add(new ExpandDataChildBean("货到付款", false));
        exList.add(new ExpandDataBean("支付方式", childBeen1));

        List<ExpandDataChildBean> childBeen2 = new ArrayList<>();
        childBeen2.add(new ExpandDataChildBean("普通快递", true));
        childBeen2.add(new ExpandDataChildBean("顺丰快递", false));
        exList.add(new ExpandDataBean("快递", childBeen2));

        List<ExpandDataChildBean> childBeen3 = new ArrayList<>();
        childBeen3.add(new ExpandDataChildBean("工作日送货", true));
        childBeen3.add(new ExpandDataChildBean("只双休日节假日送货", false));
        childBeen3.add(new ExpandDataChildBean("任何时候", false));
        exList.add(new ExpandDataBean("送货时间", childBeen3));
    }

    private void initData() {
        Intent intent = getIntent();
        SerializableBean bean = (SerializableBean) intent.getSerializableExtra("goods");
        cartList = (List<CartGoodsBean.CartBean>) bean.object;

    }


    public void payMoney(View view) {
        //支付
        MToast.notifys("此处应该计算所有商品的价格,模拟省略");


    }


    class MyAdapter extends BaseListAdapter<CartGoodsBean.CartBean> {
        public MyAdapter(List<CartGoodsBean.CartBean> list) {
            super(list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(PayLocationActivity.this, R.layout.item_pay_location_list_lv, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CartGoodsBean.CartBean good = getItem(position);
            Picasso.with(context).load(PathUtils.IMG_HEAD + good.getImgpath()).placeholder(R.drawable.product_icon_loading_default).into(holder.imgIv1);
            holder.numTv1.setText("× " + good.getNum());
            holder.titleTv1.setText(good.getTitle());
            holder.sizeTv1.setText("颜色 : " + good.getColor() + ",型号 : " + good.getSize());
            holder.titlePrice1.setText("RMB : " + good.getPrice());

            return convertView;
        }

        class ViewHolder {
            @InjectView(R.id.item_cart_list_lv_img_iv1)
            ImageView imgIv1;
            @InjectView(R.id.item_cart_list_lv_num_tv1)
            TextView numTv1;
            @InjectView(R.id.item_cart_list_lv_title_tv1)
            TextView titleTv1;
            @InjectView(R.id.item_cart_list_lv_size_tv1)
            TextView sizeTv1;
            @InjectView(R.id.item_cart_list_lv_title_price1)
            TextView titlePrice1;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }

    }

}
