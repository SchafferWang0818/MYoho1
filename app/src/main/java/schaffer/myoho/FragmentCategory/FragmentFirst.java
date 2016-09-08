package schaffer.myoho.FragmentCategory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import schaffer.myoho.Adapter.CateSonPagerAdapter;
import schaffer.myoho.FragmentCategoryChild.BaseCategoryFragment;
import schaffer.myoho.Base.BaseChildFragment;
import schaffer.myoho.FragmentCategoryChild.FragmentFirst_Boy;
import schaffer.myoho.FragmentCategoryChild.FragmentFirst_Girl;
import schaffer.myoho.FragmentCategoryChild.FragmentFirst_LifeStyle;
import schaffer.myoho.DefinedView.CategoryViewPager;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentFirst extends BaseChildFragment {

    CategoryViewPager firstGroupPager;
    private View inflate;
    private List<BaseCategoryFragment> sonFragmentList;
    private CateSonPagerAdapter adapter;
    private FragmentFirst_Girl girl;
    private FragmentFirst_LifeStyle lifeStyle;
    private FragmentFirst_Boy boy;

    @Override
    protected void initDatas() {
        sonFragmentList = new ArrayList<>();
        boy = new FragmentFirst_Boy();
        sonFragmentList.add(boy);
        girl = new FragmentFirst_Girl();
        sonFragmentList.add(girl);
        lifeStyle = new FragmentFirst_LifeStyle();
        sonFragmentList.add(lifeStyle);
    }

    @Override
    protected void initAdapter() {
        adapter = new CateSonPagerAdapter(getFragmentManager(), sonFragmentList);
        firstGroupPager.setAdapter(adapter);
        firstGroupPager.setOffscreenPageLimit(0);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_first, container, false);
        firstGroupPager = (CategoryViewPager) inflate.findViewById(R.id.first_group_pager);
        return inflate;
    }

    @OnClick({R.id.first_rb_boy, R.id.first_rb_girl, R.id.first_rb_lifeStyle})
    public void onClick(View view) {
//        new HttpUtils().cancelAll();
        switch (view.getId()) {
            case R.id.first_rb_boy:
                firstGroupPager.setCurrentItem(0, false);
                break;
            case R.id.first_rb_girl:
                firstGroupPager.setCurrentItem(1, false);
                break;
            case R.id.first_rb_lifeStyle:
                firstGroupPager.setCurrentItem(2, false);
                break;
        }
    }
}
