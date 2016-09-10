package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import schaffer.myoho.Bean.CartGoodsBean;
import schaffer.myoho.Event.AllCheckEvent;
import schaffer.myoho.Event.ButtonEnableEvent;
import schaffer.myoho.R;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/9/3.
 */
public class CartGoodsAdapter extends BaseListAdapter<CartGoodsBean.CartBean> {
    public CartGoodsAdapter(List<CartGoodsBean.CartBean> list, Context context) {
        super(list, context);
    }

    List<Integer> checks = new ArrayList<>();
    public static final int TYPE_DISABLE = 0;
    public static final int TYPE_ENABLE = 1;

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = null;
        if (convertView == null) {
            if (getItem(position).type == TYPE_DISABLE) {
                convertView = View.inflate(context, R.layout.item_fragment_cart_list_lv_1, null);
                holder = new ViewHolder1(convertView);
            } else {
                convertView = View.inflate(context, R.layout.item_fragment_cart_list_lv_2, null);
                holder = new ViewHolder2(convertView);
            }
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        if (getItem(position).type == TYPE_DISABLE) {
            final ViewHolder1 holder1 = (ViewHolder1) holder;
            final CartGoodsBean.CartBean good = getItem(position);
            Picasso.with(context).load(PathUtils.IMG_HEAD + good.getImgpath()).placeholder(R.drawable.product_icon_loading_default).into(holder1.imgIv1);
            holder1.numTv1.setText("× " + good.getNum());
            holder1.titleTv1.setText(good.getTitle());
            holder1.sizeTv1.setText("颜色 : " + good.getColor() + ",型号 : " + good.getSize());
            holder1.price1.setText("RMB : " + good.getPrice());
            holder1.checkBox1.setChecked(false);
            if (good.isChecked()) {
                holder1.checkBox1.setChecked(true);
            }
            holder1.checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAndPost(position);
                }
            });
        } else {
            final ViewHolder2 holder2 = (ViewHolder2) holder;
            final CartGoodsBean.CartBean good = getItem(position);
            holder2.price2.setText(good.getPrice());
            holder2.numTv2.setText("× " + good.getNum());
            holder2.sizeTv2.setText("颜色 : " + good.getColor() + ",型号 : " + good.getSize());
            holder2.tvNum2.setText(good.getNum());
            holder2.checkBox2.setChecked(false);
            if (good.isChecked()) {
                holder2.checkBox2.setChecked(true);
            }
            Picasso.with(context).load(PathUtils.IMG_HEAD + good.getImgpath()).placeholder(R.drawable.product_icon_loading_default).into(holder2.imgIv2);
            holder2.subtractBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击数据减少
                    String num = (String) holder2.tvNum2.getText();
                    int number = Integer.parseInt(num);
                    if (number > 0) {
                        holder2.tvNum2.setText(number - 1 + "");
                        holder2.numTv2.setText("× " + (number - 1));
                        int currentNum = Integer.parseInt(list.get(position).getNum()) - 1;
                        list.get(position).setNum(currentNum + "");
                    }
                }
            });
            holder2.addBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击数量增加
                    String num = (String) holder2.tvNum2.getText();
                    int number = Integer.parseInt(num);
                    holder2.tvNum2.setText(number + 1 + "");
                    holder2.numTv2.setText("× " + (number + 1));
                    int currentNum = Integer.parseInt(list.get(position).getNum()) + 1;
                    list.get(position).setNum(currentNum + "");
                }
            });
            holder2.checkBox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAndPost(position);
                }
            });
        }
        return convertView;
    }

    private void checkAndPost(int position) {
        boolean checked = list.get(position).checked;
        if (checked) {
            //置为未选中
            list.get(position).setChecked(false);
            EventBus.getDefault().post(new AllCheckEvent(false));
        } else {
            //置为选中
            list.get(position).setChecked(true);
            int check = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChecked()) {
                    check++;
                }
            }

            if (check == list.size()) {
                EventBus.getDefault().post(new AllCheckEvent(true));
            }
        }
        int checkNum = 0;
        for (CartGoodsBean.CartBean cartBean : list) {
            if (cartBean.checked) {
                checkNum++;
            }
        }
        setPayButtonEnable(checkNum);
    }

    private void setPayButtonEnable(int check) {
        if (check == 0) {
            MLog.w("当前应该不能点击");
            EventBus.getDefault().post(new ButtonEnableEvent(false));
            MToast.notifys("请选中一个商品");
        } else {
            MLog.w("当前应该可以点击");
            EventBus.getDefault().post(new ButtonEnableEvent(true));
        }
    }

    static class BaseViewHolder {
        BaseViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    static class ViewHolder1 extends BaseViewHolder {
        @InjectView(R.id.item_cart_list_lv_cb1)
        CheckBox checkBox1;
        @InjectView(R.id.item_cart_list_lv_img_iv1)
        ImageView imgIv1;
        @InjectView(R.id.item_cart_list_lv_num_tv1)
        TextView numTv1;
        @InjectView(R.id.item_cart_list_lv_title_tv1)
        TextView titleTv1;
        @InjectView(R.id.item_cart_list_lv_size_tv1)
        TextView sizeTv1;
        @InjectView(R.id.item_cart_list_lv_title_price1)
        TextView price1;

        ViewHolder1(View view) {
            super(view);
        }
    }

    static class ViewHolder2 extends BaseViewHolder {
        @InjectView(R.id.item_cart_list_lv_cb2)
        CheckBox checkBox2;
        @InjectView(R.id.item_cart_list_lv_img_iv2)
        ImageView imgIv2;
        @InjectView(R.id.item_cart_list_lv_num_tv2)
        TextView numTv2;
        @InjectView(R.id.item_cart_list_lv_num_subtract_btn2)
        Button subtractBtn2;
        @InjectView(R.id.item_cart_list_lv_num2)
        TextView tvNum2;
        @InjectView(R.id.item_cart_list_lv_num_add_btn2)
        Button addBtn2;
        @InjectView(R.id.item_cart_list_lv_size_tv2)
        TextView sizeTv2;
        @InjectView(R.id.item_cart_list_lv_title_price2)
        TextView price2;

        ViewHolder2(View view) {
            super(view);
        }
    }
}
