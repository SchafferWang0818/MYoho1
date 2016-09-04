package schaffer.myoho.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.HomeGvHeaderAdapter;
import schaffer.myoho.Adapter.HomeLvAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Bean.HomeBean;
import schaffer.myoho.Bean.HomeGvHeaderBean;
import schaffer.myoho.DefinedView.MGridView;
import schaffer.myoho.DefinedView.PagerDotView;
import schaffer.myoho.DefinedView.RefreshListView;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentHome extends BaseFragment implements RefreshListView.OnRefreshListener {

    private RelativeLayout hometb;
    private schaffer.myoho.DefinedView.RefreshListView homelv;
    private android.widget.ViewFlipper homeflipper;
    private List<List<HomeBean.BrandBean>> homebeanLists;
    private HomeLvAdapter adapter;

    @Override
    protected void initDatas() {
        loadData();
    }

    private void loadData() {
        new HttpUtils().loadData(PathUtils.JSON_HOME_PAGER, PathUtils.JSON_HOME_PAGE_BODY).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {

                HomeBean homeBean = new Gson().fromJson(content, HomeBean.class);
                homebeanLists.clear();
                homebeanLists.add(homeBean.getAccessories());
                homebeanLists.add(homeBean.getBrand());
                homebeanLists.add(homeBean.getMen());
                homebeanLists.add(homeBean.getMenpants());
                homebeanLists.add(homeBean.getOther());
                if (adapter == null) {
                    adapter = new HomeLvAdapter(homebeanLists, ac);
                    homelv.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                homelv.setInitialTop();
            }

            @Override
            public void loadFailed(String errorMsg) {
                MToast.notifys("home数据加载失败-->" + errorMsg);

            }
        });
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();

    }

    @Override
    protected void initListener() {
        super.initListener();
        homelv.setOnRefreshListener(this);
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
        homebeanLists = new ArrayList<>();
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
        pagerDotView.getData(PathUtils.JSON_PAGE, "");
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(210));
        pagerDotView.setLayoutParams(layoutParams);
        homelv.addHeadView(pagerDotView);
        homelv.addHeadView(gv);
    }


    @Override
    public void loadTop() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                homelv.setInitialTop();
            }
        }, 1500);
    }

    @Override
    public void loadbottom() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                homelv.setInitialBottom();
            }
        }, 1500);
    }
}
