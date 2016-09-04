package schaffer.myoho.CategoryChildFragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/24.
 */
public abstract class BaseCategoryFragment extends Fragment {


    public ListView leftLv;
    public ImageView fingerIv;
    public ListView rightLv;
    public LinearLayout rightGroup;
    private View view;
    private View inflate;
    public Activity a;
    public int width;
    private int windowWidth;
    private ObjectAnimator animator;
    private boolean isPrepared;
    private boolean isVisible;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        a = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        if (view == null) {
            view = initInflaterViews(inflater, container);
            if (isVisible && isPrepared) {
                initData();
                initAdapter();
                initListener();
            }
        }
        return view;
    }

    protected void initListener() {

    }

    protected abstract void initAdapter();

    protected abstract void initData();

    protected View initInflaterViews(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_category_child1_base, container, false);
        leftLv = (ListView) inflate.findViewById(R.id.first_base_child_lv);
        rightLv = (ListView) inflate.findViewById(R.id.first_base_child_right_lv);
        fingerIv = (ImageView) inflate.findViewById(R.id.first_base_child_finger_iv);
        rightGroup = (LinearLayout) inflate.findViewById(R.id.first_base_child_right_group);
        windowWidth = a.getWindowManager().getDefaultDisplay().getWidth();
        this.width = windowWidth / 2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rightGroup.getLayoutParams();
        layoutParams.width = width;
        rightGroup.setLayoutParams(layoutParams);
        rightGroup.setTranslationX(width);
        initRightLv();
        initAnim();
        return inflate;
    }

    private void initRightLv() {

        List<String> rightList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            rightList.add("item--->" + i);
        }
        rightLv.setAdapter(new ArrayAdapter<String>(a, android.R.layout.simple_list_item_1, rightList));
    }


    private void initAnim() {
        animator = ObjectAnimator.ofFloat(rightGroup, "translationX", 0, 0);
        animator.setDuration(300);
    }

    public void openRight() {
        animator.cancel();
        animator.setFloatValues(width,0);
        animator.start();
        isOpen = true;
    }

    public void closeRight() {
        animator.cancel();
        animator.setFloatValues(0, width);
        animator.start();
        isOpen = false;
    }


    public boolean isOpen() {
        return rightGroup.getTranslationX() == width;
    }
    public boolean isOpen = false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
    }
}
