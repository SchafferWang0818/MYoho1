package schaffer.myoho.ChildFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import schaffer.myoho.Base.BaseChildFragment;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentFirst extends BaseChildFragment {


    @Override
    protected void initDatas() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        TextView tv = new TextView(getContext());
        tv.setText(getClass().getSimpleName());
        return tv;
    }
}
