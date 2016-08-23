package schaffer.myoho.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by a7352 on 2016/8/23.
 */
public abstract class BaseChildFragment extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = initViews(inflater, container);
            initDatas();
            initAdapter();
            initListener();
        }
        return view;
    }

    protected void initListener() {

    }


    protected void initAdapter() {

    }

    protected abstract void initDatas();

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container);
}
