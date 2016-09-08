package schaffer.myoho.Fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.CategoryPagerAdapter;
import schaffer.myoho.Base.BaseChildFragment;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.FragmentCategory.FragmentFirst;
import schaffer.myoho.FragmentCategory.FragmentSecond;
import schaffer.myoho.FragmentCategory.FragmentThird;
import schaffer.myoho.DefinedView.CategoryViewPager;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentCategory extends BaseFragment {

    private android.support.design.widget.TabLayout categorytbtab;
    private android.widget.RelativeLayout categorytb;
    private schaffer.myoho.DefinedView.CategoryViewPager categorypager;
    private List<BaseChildFragment> list;
    private CategoryPagerAdapter adapter;

    @Override
    protected void initDatas() {
        list = new ArrayList<>();
        list.add(new FragmentFirst());
        list.add(new FragmentSecond());
        list.add(new FragmentThird());
    }

    @Override
    protected void initAdapter() {
        adapter = new CategoryPagerAdapter(getChildFragmentManager(), list);
        categorypager.setAdapter(adapter);
        categorytbtab.setupWithViewPager(categorypager);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        this.categorypager = (CategoryViewPager) view.findViewById(R.id.category_pager);
        this.categorytb = (RelativeLayout) view.findViewById(R.id.category_tb);
        this.categorytbtab = (TabLayout) view.findViewById(R.id.category_tb_tab);
        categorytbtab.setTabTextColors(Color.WHITE, Color.WHITE);
        categorytbtab.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
}
