package schaffer.myoho.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import schaffer.myoho.Base.BaseFragment;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentMine extends BaseFragment {


    @Override
    protected void initDatas() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        TextView tv = new TextView(ac);
        tv.setText(getClass().getSimpleName());
        return tv;
    }
}
