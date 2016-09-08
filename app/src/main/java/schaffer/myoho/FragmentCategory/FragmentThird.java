package schaffer.myoho.FragmentCategory;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import schaffer.myoho.Base.BaseChildFragment;
import schaffer.myoho.Adapter.BaseListAdapter;
import schaffer.myoho.Bean.FollowGoodsBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/23.
 */
public class FragmentThird extends BaseChildFragment {


    private ListView lv;
    private List<FollowGoodsBean.FollowBean> follow;
    private List<FollowGoodsBean.FollowBean> list = new ArrayList<>();
    private MyAdapter adapter;
    private boolean isScrolling;
    private RelativeLayout emptyView;
    private TextView evTv;
    private ProgressBar pb;

    @Override
    protected void initDatas() {

        loadData();
    }

    public void loadData() {
        new HttpUtils().loadData(PathUtils.JSON_FOLLOW, "").setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            @Override
            public void loadSuccess(String content) {

                if (content.startsWith("{") || content.startsWith("[")) {
                    FollowGoodsBean followGoodsBean = new Gson().fromJson(content, FollowGoodsBean.class);
                    follow = followGoodsBean.getFollow();
                    if (follow.size() > 0) {
                        list.clear();
                        list.addAll(follow);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    MToast.notifys("third-->数据为空?");
                }
            }

            @Override
            public void loadFailed(String errorMsg) {
                MToast.notifys("third-->" + errorMsg);
                evTv.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initAdapter() {
        super.initAdapter();
        adapter = new MyAdapter(list);
        lv.setAdapter(adapter);
        lv.setEmptyView(emptyView);
    }

    @Override
    protected void initListener() {
        super.initListener();
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        isScrolling = false;
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                    case SCROLL_STATE_FLING:
                        isScrolling = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        evTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                evTv.setVisibility(View.GONE);
                loadData();
            }
        });
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        lv = (ListView) view.findViewById(R.id.third_lv);
        emptyView = (RelativeLayout) view.findViewById(R.id.third_emptyView);
        evTv = (TextView) view.findViewById(R.id.third_emptyView_tv);
        pb = (ProgressBar) view.findViewById(R.id.third_emptyView_pb);
        return view;
    }

    class MyAdapter extends BaseListAdapter<FollowGoodsBean.FollowBean> {


        public MyAdapter(List<FollowGoodsBean.FollowBean> list) {
            super(list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(activity, R.layout.item_third_gv_inside, null);
                holder = new ViewHolder();
                holder.brandImg = (CircleImageView) convertView.findViewById(R.id.item_third_icon);
                holder.brandName = (TextView) convertView.findViewById(R.id.item_third_name);
                holder.goodDistance1 = (TextView) convertView.findViewById(R.id.item_third_price_distance1);
                holder.goodDistance2 = (TextView) convertView.findViewById(R.id.item_third_price_distance2);
                holder.goodDistance3 = (TextView) convertView.findViewById(R.id.item_third_price_distance3);
                holder.goodPrice1 = (TextView) convertView.findViewById(R.id.item_third_price_1);
                holder.goodPrice2 = (TextView) convertView.findViewById(R.id.item_third_price_2);
                holder.goodPrice3 = (TextView) convertView.findViewById(R.id.item_third_price_3);
                holder.goodImg1 = (ImageView) convertView.findViewById(R.id.item_third_iv1);
                holder.goodImg2 = (ImageView) convertView.findViewById(R.id.item_third_iv2);
                holder.goodImg3 = (ImageView) convertView.findViewById(R.id.item_third_iv3);
                holder.goodPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                holder.goodPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                holder.goodPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            FollowGoodsBean.FollowBean followBean = list.get(position);
            holder.brandName.setText(followBean.getBrandname());
            holder.goodDistance1.setText(followBean.getGoods().get(0).getDistance());
            holder.goodDistance2.setText(followBean.getGoods().get(1).getDistance());
            holder.goodDistance3.setText(followBean.getGoods().get(2).getDistance());
            holder.goodPrice1.setText(followBean.getGoods().get(0).getPrice());
            holder.goodPrice2.setText(followBean.getGoods().get(1).getPrice());
            holder.goodPrice3.setText(followBean.getGoods().get(2).getPrice());

            if (!isScrolling) {
                Picasso.with(activity).load(PathUtils.IMG_HEAD + followBean.getBrandimg()).into(holder.brandImg);
                Picasso.with(activity).load(PathUtils.IMG_HEAD + followBean.getGoods().get(0).getGoodsimg()).into(holder.goodImg1);
                Picasso.with(activity).load(PathUtils.IMG_HEAD + followBean.getGoods().get(1).getGoodsimg()).into(holder.goodImg2);
                Picasso.with(activity).load(PathUtils.IMG_HEAD + followBean.getGoods().get(2).getGoodsimg()).into(holder.goodImg3);
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView brandName;
        CircleImageView brandImg;
        ImageView goodImg1;
        TextView goodPrice1;
        TextView goodDistance1;
        ImageView goodImg2;
        TextView goodPrice2;
        TextView goodDistance2;
        ImageView goodImg3;
        TextView goodPrice3;
        TextView goodDistance3;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pb.clearAnimation();
    }
}
