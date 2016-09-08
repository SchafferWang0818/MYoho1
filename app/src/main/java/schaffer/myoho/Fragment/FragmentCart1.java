package schaffer.myoho.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import schaffer.myoho.Base.BaseFragment;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentCart1 extends BaseFragment  {


    @Override
    protected void initDatas() {

    }

    private void loadData() {

    }

    @Override
    protected void initAdapter() {
        super.initAdapter();

    }

    @Override
    protected void initListener() {
        super.initListener();

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        TextView tv = new TextView(getContext());
        tv.setText("cart");
        return tv;
    }

}
