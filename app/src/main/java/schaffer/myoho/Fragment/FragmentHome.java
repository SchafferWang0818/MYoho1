package schaffer.myoho.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.HomeGvHeaderAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Bean.HomeGvHeaderBean;
import schaffer.myoho.DefinedView.MGridView;
import schaffer.myoho.DefinedView.PagerDotView;
import schaffer.myoho.DefinedView.RefreshListView;
import schaffer.myoho.R;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentHome extends BaseFragment {

    private RelativeLayout hometb;
    private schaffer.myoho.DefinedView.RefreshListView homelv;
    private android.widget.ViewFlipper homeflipper;

    @Override
    protected void initDatas() {

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeflipper = (ViewFlipper) view.findViewById(R.id.home_flipper);
        homelv = (RefreshListView) view.findViewById(R.id.home_lv);
        hometb = (RelativeLayout) view.findViewById(R.id.home_tb);
        int height = ac.getWindowManager().getDefaultDisplay().getHeight();
        MLog.w(height + "height");
        homeflipper.setAutoStart(true);
        homeflipper.setInAnimation(ac, R.anim.flipper_in);
        homeflipper.setOutAnimation(ac, R.anim.flipper_out);
        initLvHeader();
        return view;
    }

    private void initLvHeader() {
        MGridView gv = new MGridView(getContext());
        List<HomeGvHeaderBean> list = new ArrayList<>();
        list.add(new HomeGvHeaderBean(R.drawable.btn_1, "title1"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_2, "title2"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_3, "title3"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_4, "title4"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_5, "title5"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_6, "title6"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_7, "title7"));
        list.add(new HomeGvHeaderBean(R.drawable.btn_8, "title8"));
        gv.setAdapter(new HomeGvHeaderAdapter(list, ac));
        gv.setNumColumns(4);


        PagerDotView pagerDotView = new PagerDotView(getContext());
        pagerDotView.getData(PathUtils.JSON_PAGE,"");
        homelv.addHeadView(pagerDotView);
        homelv.addHeadView(gv);
    }


}
