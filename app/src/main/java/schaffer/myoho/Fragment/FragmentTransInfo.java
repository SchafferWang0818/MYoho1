package schaffer.myoho.Fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentTransInfo extends BaseFragment {

    public boolean isAdd;

    @Override
    protected void initDatas() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        ScrollView scrollView = new ScrollView(getContext());
        TextView tv = new TextView(ac);
        tv.setTextSize(200);
        tv.setText(getClass().getSimpleName() + "........\n.......\n......\n........\n...................");
        scrollView.addView(tv);
        return scrollView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        isAdd = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAdd = false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_trans_in);
        } else {
            return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_trans_out);

        }

    }

}
