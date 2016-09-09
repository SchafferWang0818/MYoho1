package schaffer.myoho.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import schaffer.myoho.Base.BaseFragment;

/**
 * Created by a7352 on 2016/8/30.
 */
public class InfoPageFragmentAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> list;

    public InfoPageFragmentAdapter(FragmentManager fm,List<BaseFragment> list) {
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
