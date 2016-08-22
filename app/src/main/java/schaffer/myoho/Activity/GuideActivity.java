package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Base.BaseActivity;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;

public class GuideActivity extends BaseActivity implements View.OnClickListener {

    private android.support.v4.view.ViewPager guidepager;
    private List<View> pagerList;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        this.guidepager = (ViewPager) findViewById(R.id.guide_pager);
        initDatas();
        initAdapter();
    }


    private void initAdapter() {
        guidepager.setAdapter(new MPagerAdapter());
        guidepager.setPageMargin(10);
    }

    private void initDatas() {
        pagerList = new ArrayList<>();
        pagerList.add(getView(R.drawable.guide_1, false));
        pagerList.add(getView(R.drawable.guide_2, false));
        pagerList.add(getView(R.drawable.guide_3, false));
        pagerList.add(getView(R.drawable.guide_4, false));
        pagerList.add(getView(R.drawable.guide_5, true));
    }

    public View getView(int resId, boolean isLast) {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ImageView iv = new ImageView(this);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(windowWidth, windowHeight);
        iv.setLayoutParams(params);
        iv.setImageResource(resId);
        relativeLayout.addView(iv);
        if (isLast) {
            imageButton = new ImageButton(this);
            imageButton.setBackgroundResource(R.drawable.selector_guide_button);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.setMargins(0, 0, DeminUtils.dp2px(10), DeminUtils.dp2px(10));
            imageButton.setLayoutParams(layoutParams);
            imageButton.setOnClickListener(this);
            relativeLayout.addView(imageButton);
        }
        return relativeLayout;
    }


    @Override
    public void onClick(View v) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent intent = new Intent(GuideActivity.this, ChooseActivity.class);
        startActivity(intent);
        finish();
    }

    class MPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = pagerList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
