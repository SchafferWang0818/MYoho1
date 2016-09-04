package schaffer.myoho.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;

/**
 * Created by a7352 on 2016/8/23.
 */
public abstract class BaseChildFragment extends Fragment {


    private View view;
    public Activity activity;
    private boolean isVisible;
    private boolean isPrepared;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        if (view == null) {
            view = initViews(inflater, container);

            if (isVisible && isPrepared) {
                initContent();
                MLog.w(getClass().getSimpleName()+"请求数据");
            }
        }
        return view;
    }

    private void initContent() {
        initDatas();
        initAdapter();
        initListener();
    }

    protected void initListener() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
//            initContent();
        } else {
            isVisible = false;
            MToast.notifys(getClass().getSimpleName() + "未可见");
        }
    }

    protected void initAdapter() {

    }

    protected abstract void initDatas();

    protected abstract View initViews(LayoutInflater inflater, ViewGroup container);
}
