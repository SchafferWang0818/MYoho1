package schaffer.myoho.FragmentRoamChild;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Activity.WebActivity;
import schaffer.myoho.Adapter.RoamChildlvAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Bean.RoamBean;
import schaffer.myoho.DefinedView.DRefreshListView;
import schaffer.myoho.DefinedView.DotPagerView;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/9/5.
 */
public class FragmentRoamChild extends BaseFragment {

    public String path = "https://service.yoho.cn/guang/api/v2/article/getList?app_version=4.8.1&client_secret=3a12090b135db940c81121053c7edd4a&client_type=android&gender=1%2C3&os_version=android5.0%3AGoogle_Nexus_4_-_5.0.0_-_API_21_-_768x1280&page=1&screen_size=768x1184&sort_id=0&udid=&v=7&yh_channel=1";
    private schaffer.myoho.DefinedView.DRefreshListView lv;
    public List<RoamBean.DataBean.ListBean.ArtListBean> list = new ArrayList<>();
    private RoamChildlvAdapter adapter;

    @Override
    protected void initDatas() {
        new HttpUtils().loadData(path, "").setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {
                MLog.w("roamChild数据内容为-->" + content);
                RoamBean roam = new Gson().fromJson(content, RoamBean.class);
                List<RoamBean.DataBean.ListBean.ArtListBean> artList = roam.getData().getList().getArtList();
                list.addAll(artList);
                adapter = new RoamChildlvAdapter(list, ac);
                lv.setAdapter(adapter);
                initHead();
            }

            @Override
            public void loadFailed(String errorMsg) {
                MLog.e("roamChild数据加载失败-->" + errorMsg);
            }
        });
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_roam_child, container, false);
        this.lv = (DRefreshListView) view.findViewById(R.id.roam_child_refreshLv);
//        initHead();
        return view;
    }

    private void initHead() {
        DotPagerView dotPagerView = new DotPagerView(getContext());
        dotPagerView.getData(PathUtils.JSON_PAGE, "");
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(210));
        dotPagerView.setLayoutParams(layoutParams);
        lv.addHeadView(dotPagerView);
    }

    @Override
    protected void initListener() {
        super.initListener();
        lv.setOnRefreshListener(new DRefreshListView.OnRefreshListener() {
            @Override
            public void loadTop() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.setInitialTop();
                    }
                }, 3000);
            }

            @Override
            public void loadbottom() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.setInitialBottom();
                    }
                }, 3000);
            }
        });
//        lv.setItemClick(new AbsListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position > 0) {
//                    MLog.w("点击事件");
//                    String url = list.get(position - 1).getUrl();
//                    Intent intent = new Intent(ac, WebActivity.class);
//                    intent.putExtra("url", url);
//                    startActivity(intent);
//                }
//            }
//        });
        lv.setOnItemClick(new DRefreshListView.OnItemClick() {
            @Override
            public void click(int position) {
                if (position > 0) {
                    MLog.w("点击事件");
                    String url = list.get(position - 1).getUrl();
                    Intent intent = new Intent(ac, WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });

    }
}
