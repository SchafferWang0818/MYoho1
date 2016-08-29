package schaffer.myoho.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import schaffer.myoho.Base.BaseCategoryFragment;

/**
 * Created by a7352 on 2016/8/24.
 */
public class CateSonPagerAdapter extends FragmentStatePagerAdapter {
    List<BaseCategoryFragment> list;

    public CateSonPagerAdapter(FragmentManager fm, List<BaseCategoryFragment> list) {
        super(fm);
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
}
