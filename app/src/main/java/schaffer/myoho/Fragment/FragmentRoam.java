package schaffer.myoho.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.RoamPageAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.FragmentRoamChild.FragmentRoamChild;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentRoam extends BaseFragment {


    private android.support.design.widget.TabLayout roamtab;
    private android.support.v4.view.ViewPager roampager;
    private List<Fragment> list;
    private List<String> titles;

    @Override
    protected void initDatas() {
        list = new ArrayList<>();
        list.add(new FragmentRoamChild());
        list.add(new FragmentRoamChild());
        list.add(new FragmentRoamChild());
        list.add(new FragmentRoamChild());
        list.add(new FragmentRoamChild());
        list.add(new FragmentRoamChild());
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("话题");
        titles.add("搭配");
        titles.add("潮人");
        titles.add("潮品");
        titles.add("专题");
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_roam, container, false);
        this.roampager = (ViewPager) view.findViewById(R.id.roam_pager);
        this.roamtab = (TabLayout) view.findViewById(R.id.roam_tab);
        return view;
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        RoamPageAdapter adapter = new RoamPageAdapter(getFragmentManager(), titles, list);
        roampager.setAdapter(adapter);
        roamtab.setupWithViewPager(roampager);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

}
