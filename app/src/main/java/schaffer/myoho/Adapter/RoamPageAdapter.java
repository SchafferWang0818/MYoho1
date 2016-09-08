package schaffer.myoho.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by a7352 on 2016/9/5.
 */
public class RoamPageAdapter extends FragmentPagerAdapter {
    List<String> titles;
    List<Fragment> list;

    public RoamPageAdapter(FragmentManager fm, List<String> titles, List<Fragment> list) {
        super(fm);
        this.titles = titles;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
