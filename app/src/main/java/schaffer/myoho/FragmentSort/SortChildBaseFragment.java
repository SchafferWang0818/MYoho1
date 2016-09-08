package schaffer.myoho.FragmentSort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Adapter.SortBrandGvAdapter;
import schaffer.myoho.Base.BaseFragment;
import schaffer.myoho.Bean.FollowGoodsBean;
import schaffer.myoho.DefinedView.SortGridView;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/30.
 */
public class SortChildBaseFragment extends BaseFragment {


    private SortGridView gv;
    public SortBrandGvAdapter adapter;
    public List<FollowGoodsBean.FollowBean.GoodsBean> goodsBeanList;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_sort_child_base, container, false);
        gv = (SortGridView) view.findViewById(R.id.fragment_brand_info_base_gv);
        goodsBeanList = new ArrayList<>();

        setAdapter();
        return view;
    }

    @Override
    protected void initDatas() {
        MLog.w("base-->initData");
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 40 * Math.random(); i++) {
//            list.add("item--->" + i);
//        }
//        gv.setGvAdapter(new ArrayAdapter<String>(ac, android.R.layout.simple_list_item_1, list));
        loadData();
    }

    private void loadData() {
        new HttpUtils().loadData(PathUtils.JSON_FOLLOW, "params={\\\"categoryId:\\\"\" + 1 + \"}").setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {
                MLog.w("base-->" + content);
                FollowGoodsBean followGoodsBean = new Gson().fromJson(content, FollowGoodsBean.class);
                List<FollowGoodsBean.FollowBean> follows = followGoodsBean.getFollow();
                for (FollowGoodsBean.FollowBean follow : follows) {
                    List<FollowGoodsBean.FollowBean.GoodsBean> goods = follow.getGoods();
                    for (FollowGoodsBean.FollowBean.GoodsBean good : goods) {
                        goodsBeanList.add(good);
                    }
                }
                MLog.w("adapter-->" + adapter);
                setAdapter();
                MToast.notifys("数据已经加载成功...");
            }

            @Override
            public void loadFailed(String errorMsg) {
                MToast.notifys("加载失败-->" + errorMsg);
            }
        });
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new SortBrandGvAdapter(goodsBeanList, ac);
            gv.setGvAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        gv.setOnLoadListener(new SortGridView.OnLoadListener() {
            @Override
            public void loadHead() {
                loadData();
                gv.initHeadState();
                MToast.notifys("下拉加载成功");
            }

            @Override
            public void loadFoot() {
                gv.initFootState();
                MToast.notifys("上拉加载成功");

            }
        });
    }

    /**
     * 升序
     */
    public void ascendingOrder() {

    }

    /**
     * 降序
     */
    public void descendingOrder() {

    }

    /**
     * 排序
     */
    public void sort() {

    }


}
