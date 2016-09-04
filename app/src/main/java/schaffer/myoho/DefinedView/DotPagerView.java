package schaffer.myoho.DefinedView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Bean.PagerViewBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/25.
 */
public class DotPagerView extends RelativeLayout {

    //    public String path = "http://www.iwens.org/School_Sky/yohoadvert.php";
    private ViewPager pager;
    private LinearLayout dotGroup;
    public List<PagerViewBean> dataBeans = new ArrayList<>();
    public List<ImageView> pageImgList = new ArrayList<>();
    private boolean drag;

    public DotPagerView(Context context) {
        this(context, null);
    }

    public DotPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initChildView();
    }

    private void initChildView() {
        pager = new ViewPager(getContext());
        RelativeLayout.LayoutParams pagerParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, DeminUtils.dp2px(200));
        pager.setLayoutParams(pagerParams);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                MLog.w("onPageSelected->"+position);
                selectDot(position % pageImgList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        drag = true;
                        stopLoop();
                        break;

                    case ViewPager.SCROLL_STATE_SETTLING:
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (drag == true) {
                            drag = false;
                            startLoop();
                        }
                        break;
                }
            }
        });
        addView(pager);

        dotGroup = new LinearLayout(getContext());
        dotGroup.setOrientation(LinearLayout.HORIZONTAL);
        dotGroup.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams groupParams = new LayoutParams(LayoutParams.WRAP_CONTENT, DeminUtils.dp2px(10));
        groupParams.addRule(CENTER_HORIZONTAL);
        groupParams.addRule(ALIGN_PARENT_BOTTOM);
        groupParams.bottomMargin = DeminUtils.dp2px(40);
        dotGroup.setLayoutParams(groupParams);
        addView(dotGroup);
    }

    private void selectDot(int position) {
        for (int i = 0; i < pageImgList.size(); i++) {
            View dot = dotGroup.getChildAt(i);
            dot.setSelected(false);
            if (i == position) {
                dot.setSelected(true);
            }
        }
    }

    public View getDot(boolean selected) {
        View dot = new View(getContext());
        LinearLayout.MarginLayoutParams dotParams = new LinearLayout.LayoutParams(DeminUtils.dp2px(10), DeminUtils.dp2px(10));
        dotParams.leftMargin = DeminUtils.dp2px(10);
        dot.setLayoutParams(dotParams);
        dot.setBackgroundResource(R.drawable.selector_dot_pagerdotview);
        dot.setSelected(selected);
        return dot;
    }

    public void getData(String path, String requestBody) {
        new HttpUtils().loadData(path, requestBody).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {
                MLog.w(content);
                List<PagerViewBean> list = new Gson().fromJson(content, new TypeToken<List<PagerViewBean>>() {
                }.getType());
                dataBeans.clear();
                if (list != null) {
                    dataBeans.addAll(list);
                    pageImgList = new ArrayList<>();
//                    pageImgList.clear();
                    MLog.w(dataBeans.toString());
                    for (int i = 0; i < dataBeans.size(); i++) {
                        ImageView iv = new ImageView(getContext());
//                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        MLog.w(PathUtils.IMG_HEAD + dataBeans.get(i).getImgpath());
                        Picasso.with(getContext()).load(PathUtils.IMG_HEAD + dataBeans.get(i).getImgpath()).fit().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv);
                        pageImgList.add(iv);
                        dotGroup.addView(getDot(i == 0));
                    }
                    pager.setAdapter(new MyPageAdapter());
                    pager.setCurrentItem(pageImgList.size() * 500);
                    startLoop();
                }
            }

            @Override
            public void loadFailed(String errorMsg) {
                MLog.w("图片json加载失败" + errorMsg);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = pager.getCurrentItem();
            pager.setCurrentItem(currentItem + 1);
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };

    public void startLoop() {
        handler.sendEmptyMessageDelayed(0, 4500);
    }

    public void stopLoop() {
        handler.removeCallbacksAndMessages(null);
    }

    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
//            MLog.w("pageImgList.size()>0?"+(pageImgList.size()>0));
            ImageView imageView = pageImgList.get(position % pageImgList.size());
            ViewParent parent = imageView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(imageView);
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);

        }
    }


}
