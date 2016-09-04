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

import schaffer.myoho.Adapter.BaseListAdapter;
import schaffer.myoho.Bean.CateLifeBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * FragmentFirst_LifeStyle
 */
public class FragmentFirst_LifeStyle extends BaseCategoryFragment implements HttpUtils.OnLoadDataListener, AdapterView.OnItemClickListener {

    private List<CateLifeBean.LifeBean> list;
    private LifeAdapter adapter;

    //已经加载了布局,现在就要获得数据并进行填充
    @Override
    protected void initAdapter() {
        adapter = new LifeAdapter(list);
        leftLv.setAdapter(adapter);
        leftLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
//        getData();
    }

    public void getData() {
        new HttpUtils().loadData(PathUtils.JSON_CATE_LIFESTYLE, "").setOnLoadDataListener(this);
    }

    @Override
    public void loadSuccess(String content) {
        if (content.indexOf("life")>0){
            CateLifeBean lifeBean = new Gson().fromJson(content, CateLifeBean.class);
            List<CateLifeBean.LifeBean> life = lifeBean.getLife();
            if (life != null) {
                list.clear();
                list.addAll(life);
                adapter.notifyDataSetChanged();
            }
        }else{
            MLog.e("当前json不属于boy");
        }
    }

    @Override
    public void loadFailed(String errorMsg) {
        Toast.makeText(a, errorMsg, Toast.LENGTH_SHORT).show();
    }

    int lastPosition = 0;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //得到点击当前的position,然后得到当前的位置
        if (adapter.getCount() > 0) {
            View child = leftLv.getChildAt(position);
            int top = child.getTop();
            int childMeasuredHeight = child.getMeasuredHeight();
            fingerIv.setTranslationY(top + childMeasuredHeight / 2);
            if (isOpen()) {
                if (lastPosition == position) {
                    closeRight();

                } else {

                }
            } else {
                openRight();
            }
        }
    }


    class LifeAdapter extends BaseListAdapter<CateLifeBean.LifeBean> {

        public LifeAdapter(List<CateLifeBean.LifeBean> list) {
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
