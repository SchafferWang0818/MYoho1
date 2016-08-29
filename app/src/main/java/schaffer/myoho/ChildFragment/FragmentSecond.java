package schaffer.myoho.ChildFragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import schaffer.myoho.Adapter.MExAdapter;
import schaffer.myoho.Base.BaseChildFragment;
import schaffer.myoho.Bean.AllBrandBean;
import schaffer.myoho.Bean.AllBrandLetterBean;
import schaffer.myoho.DefinedView.ForSearch.MySearchView;
import schaffer.myoho.DefinedView.HorizontalListView;
import schaffer.myoho.DefinedView.PagerDotView;
import schaffer.myoho.Event.SecondSwitchEvent;
import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentSecond extends BaseChildFragment {

    private android.widget.ExpandableListView exLv;
    private android.widget.ListView letterLv;
    private android.widget.TextView evTv;
    private android.widget.ProgressBar evPb;
    private android.widget.RelativeLayout emptyView;
    private View searchView;
    private PagerDotView pagerDotView;
    private HorizontalListView horLv;
    private List<AllBrandLetterBean> letterBeanList;
    private int currentChild;
    private RelativeLayout parentGroup;
    private RelativeLayout group;
    private AllBrandBean allBrandBean;
    private MExAdapter adapter;

    @Override
    protected void initDatas() {
        getData();
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        parentGroup = (RelativeLayout) view.findViewById(R.id.second_group);
        group = (RelativeLayout) view.findViewById(R.id.second_child_group);
        this.emptyView = (RelativeLayout) view.findViewById(R.id.second_emptyview);
        this.evPb = (ProgressBar) view.findViewById(R.id.second_ev_pb);
        this.evTv = (TextView) view.findViewById(R.id.secon_ev_tv);
        this.letterLv = (ListView) view.findViewById(R.id.second_letter_lv);
        this.exLv = (ExpandableListView) view.findViewById(R.id.second_exLv);
        evTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evTv.setVisibility(View.GONE);
                evPb.setVisibility(View.VISIBLE);
                getData();
            }
        });
        initExLv();
        initHeaderView();

        return view;
    }

    private void initHeaderView() {
        pagerDotView = new PagerDotView(activity);
        ExpandableListView.LayoutParams pageParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(180));
        pagerDotView.setLayoutParams(pageParams);

        searchView = View.inflate(activity, R.layout.item_cate_brand_head_search, null);
        ExpandableListView.LayoutParams searchParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(70));
        searchView.setLayoutParams(searchParams);

        horLv = new HorizontalListView(activity);
        ExpandableListView.LayoutParams horlvParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(180));
        horLv.setLayoutParams(horlvParams);

        horLv.loadData(PathUtils.JSON_HORIZONTAL_RV, "");
        exLv.addHeaderView(View.inflate(activity, R.layout.item_cate_brand_head_type, null));
        exLv.addHeaderView(horLv);
        exLv.addHeaderView(pagerDotView);
        exLv.addHeaderView(searchView);
    }

    private void initExLv() {
        exLv.setHeaderDividersEnabled(false);
        exLv.setEmptyView(emptyView);
        exLv.setGroupIndicator(null);
        exLv.setVerticalScrollBarEnabled(false);//设置滚动条可见
        exLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    public void getData() {
        new HttpUtils().loadData(PathUtils.JSON_CATE_ALL_BRAND_HEAD, PathUtils.ALL_BRAND_BODY)
                .setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {

                    @Override
                    public void loadSuccess(String content) {
                        if (content.startsWith("{") || content.startsWith("[")) {
                            allBrandBean = new Gson().fromJson(content, AllBrandBean.class);
                            List<AllBrandBean.BrandBean> brandsList;
                            List<String> letterList;
                            brandsList = allBrandBean.getBrand();
                            letterList = getLetter(brandsList);
                            Collections.sort(letterList);
                            letterBeanList = new ArrayList<>();
                            for (String s : letterList) {
                                //根据首字母,筛选出对应letter的brandBean的集合?
                                AllBrandLetterBean letterBean = getLetterBean(s, brandsList);
                                if (letterBean != null) {
                                    letterBeanList.add(letterBean);
                                }
                            }
                            MLog.w("存放顺序排序的LetterList:" + letterBeanList.toString());
                            adapter = new MExAdapter(letterBeanList, activity);
                            exLv.setAdapter(adapter);

                            exLv.post(new Runnable() {
                                @Override
                                public void run() {
//                                    exLv.getChildAt(0).setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//
//                                        }
//                                    });
                                    adapter.notifyDataSetChanged();


                                }
                            });

                            letterLv.setVerticalScrollBarEnabled(false);
                            letterLv.setDivider(new BitmapDrawable());
                            letterLv.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, letterList));
                            //完成点击切换二层ListView的位置,item的背景选择器
                            openAllGroup();
                            letterLv.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    switch (event.getAction()) {
                                        case MotionEvent.ACTION_DOWN:
                                        case MotionEvent.ACTION_MOVE:
                                            int currentPosition = letterLv.pointToPosition(0, (int) event.getY());
                                            if (currentPosition >= 0) {
                                                exLv.setSelectedGroup(currentPosition);
                                                currentChild = currentPosition - letterLv.getFirstVisiblePosition();
                                                MLog.w("currentPosition==currentChild?" + currentChild + "," + currentPosition);
                                                for (int i = 0; i < letterLv.getChildCount(); i++) {
                                                    letterLv.getChildAt(i).setBackgroundColor(Color.WHITE);
                                                    if (i == currentChild) {
                                                        letterLv.getChildAt(i).setBackgroundColor(Color.GRAY);
                                                    }
                                                }
                                            }
                                            break;
                                        case MotionEvent.ACTION_UP:
                                            for (int i = 0; i < letterLv.getChildCount(); i++) {
                                                letterLv.getChildAt(i).setBackgroundColor(Color.WHITE);
                                            }
                                            currentChild = -1;
                                            break;
                                    }
                                    return true;
                                }
                            });
                        } else {
                            MToast.notifys("全部品牌JSON加载内容为空");
                        }
                    }

                    @Override
                    public void loadFailed(String errorMsg) {
                        MToast.notifys("全部品牌JSON加载错误:" + errorMsg);

                        evPb.setVisibility(View.INVISIBLE);
                        evTv.setVisibility(View.VISIBLE);
                    }
                });


    }

    private void openAllGroup() {
        for (int i = 0; i < letterBeanList.size(); i++) {
            exLv.expandGroup(i,false);
        }
    }

    public List<String> getLetter(List<AllBrandBean.BrandBean> list) {
        List<String> letterList = new ArrayList<>();
        for (AllBrandBean.BrandBean brandBean : list) {
            String letter = brandBean.getLetter();
//            letterList.add(letter);
            if (!letterList.contains(letter)) {
                letterList.add(letter);
            }
        }
        return letterList;
    }

    /**
     * 返回一个包含有固定letter的集合的自定义类对象
     *
     * @param letter
     * @param list
     * @return
     */
    public AllBrandLetterBean getLetterBean(String letter, List<AllBrandBean.BrandBean> list) {
        List<AllBrandBean.BrandBean> temp = new ArrayList<>();
        for (AllBrandBean.BrandBean brandBean : list) {
            if (letter.equals(brandBean.getLetter())) {
                temp.add(brandBean);
            }
        }
        if (temp.size() > 0) {
            return new AllBrandLetterBean(letter, temp);
        }
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchPinPai(SecondSwitchEvent event) {
        parentGroup.removeAllViews();
        int i = event.i;
        if (i == 0) {
            parentGroup.addView(group);
        } else {
            MySearchView searchView = new MySearchView(activity);
            searchView.setAllBrand(allBrandBean);
            searchView.init(activity);
        }

    }
}
