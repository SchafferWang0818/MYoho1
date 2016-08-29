package schaffer.myoho.CategoryChildFragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Base.BaseCategoryFragment;
import schaffer.myoho.Adapter.BaseListAdapter;
import schaffer.myoho.Bean.CateBoyBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

public class FragmentFirst_Boy extends BaseCategoryFragment implements HttpUtils.OnLoadDataListener, AdapterView.OnItemClickListener {

    private List<CateBoyBean.BoyBean> list;
    private BoyAdapter adapter;

    //已经加载了布局,现在就要获得数据并进行填充
    @Override
    protected void initAdapter() {
        MLog.w("initAdapter");
        adapter = new BoyAdapter(list);
        leftLv.setAdapter(adapter);
        leftLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        getData();
    }

    public void getData() {
        new HttpUtils().loadData(PathUtils.JSON_CATE_BOY, "").setOnLoadDataListener(this);
    }

    @Override
    public void loadSuccess(String content) {
        if (content.indexOf("boy") > 0) {
            CateBoyBean boyBean = new Gson().fromJson(content, CateBoyBean.class);
            MLog.w("boyBean.toString()--->" + boyBean.toString());
            List<CateBoyBean.BoyBean> boy = boyBean.getBoy();
            MLog.w("boy--->" + boy);
            if (boy != null) {
                list.clear();
                list.addAll(boy);
                adapter.notifyDataSetChanged();
            }
        } else {
            MLog.e("当前json不属于boy");
        }
    }

    @Override
    public void loadFailed(String errorMsg) {
        Toast.makeText(a, errorMsg, Toast.LENGTH_SHORT).show();
    }

    int lastPosition = -1;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //得到点击当前的position,然后得到当前的位置
        if (adapter.getCount() >= 0) {
            moveFinger(position);
            MLog.w("" + isOpen()+"---->"+position);
            if (isOpen()) {
                if (lastPosition == position) {
                    closeRight();
                    MLog.w("打开时,位置相同则关闭");
                } else {
                    //切换数据
                    requestChildData();
                    MLog.w("打开时,位置不同不变化");
                }
            } else {
                openRight();
                if (lastPosition!=position){
                    requestChildData();
                }
            }
            lastPosition = position;
        }
    }

    public void requestChildData() {

    }

    private void moveFinger(int position) {
        View child = leftLv.getChildAt(position - leftLv.getFirstVisiblePosition());
        int top = child.getTop();
        int childMeasuredHeight = child.getMeasuredHeight();
        fingerIv.setTranslationY(top + childMeasuredHeight / 2);
    }


    class BoyAdapter extends BaseListAdapter<CateBoyBean.BoyBean> {

        public BoyAdapter(List<CateBoyBean.BoyBean> list) {
            super(list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(a, R.layout.item_cate_first_base_left_lv, null);
                holder = new ViewHolder();
                holder.iv = (ImageView) convertView.findViewById(R.id.item_first_base_left_icon);
                holder.typeTv = (TextView) convertView.findViewById(R.id.item_first_base_left_type_nametv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.iv.setImageResource(R.drawable.goods_vip2_icon);
            holder.typeTv.setText(list.get(position).getName());
            return convertView;
        }
    }

    class ViewHolder {
        ImageView iv;
        TextView typeTv;
    }
}
