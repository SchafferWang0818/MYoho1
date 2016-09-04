package schaffer.myoho.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import schaffer.myoho.Adapter.BaseListAdapter;
import schaffer.myoho.Adapter.GoodDetailPageAdapter;
import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.Base.BaseAnimatorListener;
import schaffer.myoho.Bean.GoodsDetailBean;
import schaffer.myoho.DefinedView.MListView;
import schaffer.myoho.Dialog.AddCartDialog;
import schaffer.myoho.Dialog.LoadDialog;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/31.
 */
public class GoodActivity extends BaseActivity implements AddCartDialog.OnAddToCartListener {
    private android.widget.RelativeLayout goodsdetailtoolbar;
    private android.widget.ImageButton cartBtn;
    private android.widget.Button addBtn;
    private android.widget.ImageButton likeBtn;
    private android.widget.RelativeLayout goodsdetailcartgroup;
    private android.support.v4.view.ViewPager pager;
    private android.widget.TextView detailTv;
    private android.widget.TextView priceTv;
    private schaffer.myoho.DefinedView.MListView lv;
    private List<GoodsDetailBean.GoodsBean> detailList;
    private List<GoodsDetailBean.ImgBean> imgBeen;
    private List<GoodsDetailBean.ImgvaleBean> lvList;
    private List<ImageView> pageList;
    private List<ImageView> ivList;
    private AddCartDialog dialog;
    private GoodsDetailBean detailBean;
    private RelativeLayout group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);

        initViews();
        initData();
        initListener();
    }

    private void initListener() {

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailBean != null) {
                    dialog = new AddCartDialog(GoodActivity.this, detailBean);
                    dialog.setOnAddToCartListener(GoodActivity.this);
                    dialog.show();
                } else {
                    MToast.notifys("数据没有准备好,无法选择");
                }
            }
        });
    }

    private void initAdapter() {
        String name = detailList.get(0).getTitle();
        String price = detailList.get(0).getPrice();
        detailTv.setText(name);
        priceTv.setText(price);
        pageList = new ArrayList<>();
        for (GoodsDetailBean.ImgBean imgBean : imgBeen) {
            ImageView iv = new ImageView(this);
            String imgpath = imgBean.getImgpath();
            Picasso.with(this).load(PathUtils.IMG_HEAD + imgpath).fit().placeholder(R.drawable.product_icon_loading_default).into(iv);
            pageList.add(iv);
        }
        GoodDetailPageAdapter pageAdapter = new GoodDetailPageAdapter(pageList);
        pager.setAdapter(pageAdapter);
        ivList = new ArrayList<>();
        for (GoodsDetailBean.ImgvaleBean imgvaleBean : lvList) {
            String imgpath = imgvaleBean.getImgpath();
            ImageView iv = new ImageView(this);
            Picasso.with(this).load(PathUtils.IMG_HEAD + imgpath).fit().into(iv);
            ivList.add(iv);
        }
        lv.setAdapter(new MyLvAdapter(ivList, this));

    }

    private void initData() {
        final LoadDialog loadDialog = new LoadDialog(this);
        loadDialog.show();
        new HttpUtils().loadData(PathUtils.JSON_GOODS_DETAIL, PathUtils.JSON_GOODS_DETAIL_BODY).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {
//                MLog.w("获得的内容是--->" + content);
                detailBean = new Gson().fromJson(content, GoodsDetailBean.class);
                detailList = detailBean.getGoods();
                imgBeen = detailBean.getImg();
                lvList = detailBean.getImgvale();
                initAdapter();
                loadDialog.dismiss();
            }

            @Override
            public void loadFailed(String errorMsg) {
                MLog.w("加载失败--->" + errorMsg);
                loadDialog.dismiss();
            }
        });

    }


    private void initViews() {
        group = (RelativeLayout) findViewById(R.id.goods_detail_group);
        this.lv = (MListView) findViewById(R.id.goods_detail_detail_detail_lv);
        this.priceTv = (TextView) findViewById(R.id.goods_detail_price_tv);
        this.detailTv = (TextView) findViewById(R.id.goods_detail_detail_tv);
        this.pager = (ViewPager) findViewById(R.id.goods_detail_img_page);
        this.likeBtn = (ImageButton) findViewById(R.id.goods_detail_like_btn);
        this.addBtn = (Button) findViewById(R.id.goods_detail_add_btn);
        this.cartBtn = (ImageButton) findViewById(R.id.goods_detail_cart_btn);
        pager.post(new Runnable() {
            @Override
            public void run() {
                group.scrollTo(0, 0);
            }
        });
    }

    @Override
    public void add() {
        final CircleImageView circleImageView = new CircleImageView(this);
        circleImageView.setBorderColor(Color.BLUE);
        circleImageView.setBorderWidth(DeminUtils.dp2px(2));
        Picasso.with(this).load(PathUtils.IMG_HEAD + imgBeen.get(0).getImgpath()).fit().into(circleImageView);
        circleImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(DeminUtils.dp2px(40), DeminUtils.dp2px(40));
        ivParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        circleImageView.setLayoutParams(ivParams);
        group.addView(circleImageView);

        group.post(new Runnable() {
            @Override
            public void run() {
                final int[] coordinateIv = new int[2];
                final int[] coordinateCart = new int[2];
                cartBtn.getLocationOnScreen(coordinateCart);
                circleImageView.getLocationOnScreen(coordinateIv);
//        TranslateAnimation trans = new TranslateAnimation(coordinateIv[0], Animation.RELATIVE_TO_PARENT,coordinateCart[0],Animation.RELATIVE_TO_PARENT,)
                ObjectAnimator anim = ObjectAnimator.ofFloat(circleImageView, "translationX", 0, coordinateCart[0] - coordinateIv[0]/*-DeminUtils.dp2px(400)*/);
                anim.setDuration(6000);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float percent = 1 - circleImageView.getTranslationX() / (coordinateCart[0] - coordinateIv[0]/*-DeminUtils.dp2px(400)*/) * 1.0f;
                        MLog.w("动画百分比-->" + percent);
                        circleImageView.setScaleX(percent);
                        circleImageView.setScaleY(percent);
                        circleImageView.setAlpha(percent);
                        circleImageView.setTranslationY((coordinateCart[1] - coordinateIv[0] - circleImageView.getMeasuredHeight()) * 1.0f * circleImageView.getTranslationX() / (coordinateCart[0] - coordinateIv[0] + cartBtn.getMeasuredWidth() / 2));
                    }
                });
                anim.addListener(new BaseAnimatorListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        group.removeView(circleImageView);
                    }
                });
                anim.start();
            }
        });

    }


    class MyLvAdapter extends BaseListAdapter<ImageView> {

        public MyLvAdapter(List<ImageView> list, Context context) {
            super(list, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = getItem(position);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //参数决定高度
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(200));
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
    }


}
