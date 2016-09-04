package schaffer.myoho.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.InfoPageFragmentAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.DefinedView.DTabView;
import schaffer.myoho.InfoChildFragment.FragmentSortChild1;
import schaffer.myoho.InfoChildFragment.FragmentSortChild2;
import schaffer.myoho.InfoChildFragment.FragmentSortChild3;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentInfo extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private DTabView tabView1;
    private DTabView tabView2;
    private DTabView tabView3;
    private android.support.v4.view.ViewPager pager;
    private List<DTabView> tabs;
    private List<BaseFragment> pageList;
    private InfoPageFragmentAdapter adapter;

    @Override
    protected void initDatas() {
        pageList = new ArrayList<>();
        pageList.add(new FragmentSortChild1());
        pageList.add(new FragmentSortChild2());
        pageList.add(new FragmentSortChild3());
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        adapter = new InfoPageFragmentAdapter(getFragmentManager(), pageList);
        pager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        for (DTabView tab : tabs) {
            tab.setOnClickListener(this);
        }
        pager.setOnPageChangeListener(this);

    }


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_sort_info, container, false);
        this.pager = (ViewPager) view.findViewById(R.id.fragment_info_pager);
        this.tabView3 = (DTabView) view.findViewById(R.id.fragment_tab_3);
        this.tabView2 = (DTabView) view.findViewById(R.id.fragment_tab_2);
        this.tabView1 = (DTabView) view.findViewById(R.id.fragment_tab_1);
        tabs = new ArrayList<>();
        tabs.add(tabView1);
        tabs.add(tabView2);
        tabs.add(tabView3);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        for (DTabView tab : tabs) {
            tab.setSelected(false);
        }
        switch (v.getId()) {
            case R.id.fragment_tab_1:
                tabView1.setSelected(true);
                pager.setCurrentItem(0);
                break;
            case R.id.fragment_tab_2:
                tabView2.setSelected(true);
                pager.setCurrentItem(1);
                break;
            case R.id.fragment_tab_3:
                tabView3.setSelected(true);
                pager.setCurrentItem(2);
                break;

        }
    }
}
