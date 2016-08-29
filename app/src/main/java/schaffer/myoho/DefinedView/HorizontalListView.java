package schaffer.myoho.DefinedView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import schaffer.myoho.Bean.HotBrandBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MToast;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/25.
 */
public class HorizontalListView extends LinearLayout {

    private TextView tv;
    private RecyclerView recycler;
    private List<HotBrandBean.BrandBean> brand;

    public HorizontalListView(Context context) {
        this(context, null);
    }

    public HorizontalListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.item_cate_bread_head_horizontal, null);
        addView(view);
        tv = (TextView) view.findViewById(R.id.item_brand_main_tv);
        recycler = (RecyclerView) view.findViewById(R.id.item_head_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void loadData(String path, String requestBody) {
        new HttpUtils().loadData(path, requestBody).setOnLoadDataListener(new HttpUtils.OnLoadDataListener() {
            private List<HotBrandBean.BrandBean> brand;

            @Override
            public void loadSuccess(String content) {
                boolean isJson = content.startsWith("{") || content.startsWith("[");
                if (isJson) {
                    HotBrandBean hotBrandBean = new Gson().fromJson(content, HotBrandBean.class);
                    brand = hotBrandBean.getBrand();
                    recycler.setAdapter(new MyAdapter());
                } else {
                    MToast.notifys("加载recycler的json为空");
                }
            }

            @Override
            public void loadFailed(String errorMsg) {
                MToast.notifys("recycler:" + errorMsg);
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MRecyclerHolder> {

        @Override
        public MRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MRecyclerHolder(View.inflate(getContext(), R.layout.item_cate_brand_horizontallv, null));
        }

        @Override
        public void onBindViewHolder(MRecyclerHolder holder, int position) {
            HotBrandBean.BrandBean brandBean = brand.get(position);
            holder.tv.setText(brandBean.getName());
            Picasso.with(getContext()).load(PathUtils.IMG_HEAD + brandBean.getImgpath())
                    .placeholder(R.mipmap.ic_launcher).into(holder.iv);
        }

        @Override
        public int getItemCount() {
            return brand.size();
        }
    }


    private class MRecyclerHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public MRecyclerHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.item_hor_tv);
            iv = (ImageView) itemView.findViewById(R.id.item_hor_iv);
        }
    }
}
