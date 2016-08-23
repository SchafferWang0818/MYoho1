package schaffer.myoho.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import schaffer.myoho.Base.BaseChildFragment;

/**
 * Created by a7352 on 2016/8/23.
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {
    String[] titles = new String[]{"品类", "品牌", "关注"};
    List<BaseChildFragment> fragmentList;

    public CategoryPagerAdapter(FragmentManager fm, List<BaseChildFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
