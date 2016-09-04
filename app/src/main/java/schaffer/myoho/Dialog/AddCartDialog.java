package schaffer.myoho.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import schaffer.myoho.Bean.GoodsDetailBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/9/1.
 */
public class AddCartDialog extends BaseDialog {


    private android.widget.ImageView iv;
    private android.widget.TextView nameTv;
    private android.widget.TextView priceTv;
    private android.widget.LinearLayout content;
    private android.widget.Button addToCart;
    private android.widget.RadioButton rbColor1;
    private android.widget.RadioButton rbColor2;
    private android.widget.RadioButton rbColor3;
    private android.widget.RadioButton rbsize1;
    private android.widget.RadioButton rbsize2;
    private android.widget.RadioButton rbsize3;
    private android.widget.RadioButton rbsize4;
    private android.widget.Button subtractBtn;
    private android.widget.TextView numtv;
    private android.widget.Button addBtn;
    private final View view;

    public AddCartDialog(Context context, GoodsDetailBean detailBean) {
        super(context);
        view = View.inflate(context, R.layout.dialog_add_cart, null);
        setContentView(view);
        initLayout();
        initView();
        initData(context, detailBean);
        initListener();

    }

    private void initData(Context context, GoodsDetailBean detailBean) {
        MLog.w(getClass().getSimpleName()+"-->"+detailBean);
        if (detailBean != null) {
            String title = detailBean.getGoods().get(0).getTitle();
            String price = detailBean.getGoods().get(0).getPrice();
            priceTv.setText(price);
            nameTv.setText(title);
            List<GoodsDetailBean.ImgBean> img = detailBean.getImg();
            String imgpath = img.get(0).getImgpath();
            Picasso.with(context).load(PathUtils.IMG_HEAD + imgpath).placeholder(R.drawable.product_icon_loading_default).into(iv);
        }
    }

    private void initListener() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = (String) numtv.getText();
                int number = Integer.parseInt(num);
                numtv.setText((number + 1) + "");
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = (String) numtv.getText();
                int number = Integer.parseInt(num);
                if (number > 0) {
                    numtv.setText((number - 1) + "");
                }
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddToCartListener != null) {
                    dismiss();
                    onAddToCartListener.add();
                }
            }
        });
    }

    private void initLayout() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#999999"));
//        getWindow().setBackgroundDrawable(new BitmapDrawable());
        view.measure(0, View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.EXACTLY));
        MLog.w(getClass().getSimpleName()+"-->"+view.getMeasuredHeight()+"---->"+windowHeight);
        getWindow().setLayout(windowWidth, DeminUtils.dp2px(350));
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.style_dialog_add);
    }

    private void initView() {
        this.addBtn = (Button) findViewById(R.id.dialog_good_add_add_btn);
        this.numtv = (TextView) findViewById(R.id.dialog_good_add_num_tv);
        this.subtractBtn = (Button) findViewById(R.id.item_cart_list_lv_num_subtract_btn2);
        this.rbsize4 = (RadioButton) findViewById(R.id.dialog_good_add_choose_size_4);
        this.rbsize3 = (RadioButton) findViewById(R.id.dialog_good_add_choose_size_3);
        this.rbsize2 = (RadioButton) findViewById(R.id.dialog_good_add_choose_size_2);
        this.rbsize1 = (RadioButton) findViewById(R.id.dialog_good_add_choose_size_1);
        this.rbColor3 = (RadioButton) findViewById(R.id.dialog_good_add_choose_color_3);
        this.rbColor2 = (RadioButton) findViewById(R.id.dialog_good_add_choose_color_2);
        this.rbColor1 = (RadioButton) findViewById(R.id.dialog_good_add_choose_color_1);
        this.addToCart = (Button) findViewById(R.id.dialog_good_add_btn);
        this.content = (LinearLayout) findViewById(R.id.dialog_good_detail);
        this.priceTv = (TextView) findViewById(R.id.dialog_good_add_price);
        this.nameTv = (TextView) findViewById(R.id.dialog_good_add_name);
        this.iv = (ImageView) findViewById(R.id.dialog_add_detail_iv);

    }

    public interface OnAddToCartListener {
        void add();
    }

    OnAddToCartListener onAddToCartListener;

    public void setOnAddToCartListener(OnAddToCartListener onAddToCartListener) {
        this.onAddToCartListener = onAddToCartListener;
    }
}
