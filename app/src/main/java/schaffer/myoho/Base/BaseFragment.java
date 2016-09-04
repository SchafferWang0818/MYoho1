package schaffer.myoho.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by a7352 on 2016/8/23.
 */
public abstract class BaseFragment extends Fragment {

    public Activity ac;
    private View view;
    private boolean isVisible;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ac = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = initViews(inflater, container);
//            if (isVisible) {
                initDatas();
                initAdapter();
                initListener();
//            }
        }
        return view;
    }

    protected void initListener() {

    }


    protected void initAdapter() {

    }

    protected abstract void initDatas();

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container);


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        MLog.w("setUserVisibleHint");
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//            isVisible = true;
//            load();
//        } else {
//            isVisible = false;
//        }
//    }

    /**
     * 加载一些内容
     */
    protected void load() {

    }
}
