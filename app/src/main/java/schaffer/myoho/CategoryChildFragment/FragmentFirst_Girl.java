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
import schaffer.myoho.Bean.CateGirlBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.HttpUtils;
import schaffer.myoho.Utils.MLog;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/24.FragmentFirst_Girl
 */

/**
 * Created by a7352 on 2016/8/24.
 */
public class FragmentFirst_Girl extends BaseCategoryFragment implements HttpUtils.OnLoadDataListener, AdapterView.OnItemClickListener {

    private List<CateGirlBean.GirlBean> list;
    private GirlAdapter adapter;

    //已经加载了布局,现在就要获得数据并进行填充
    @Override
    protected void initAdapter() {
        adapter = new GirlAdapter(list);
        leftLv.setAdapter(adapter);
        leftLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
//        getData();
    }

    public void getData() {
        new HttpUtils().loadData(PathUtils.JSON_CATE_GIRL, "").setOnLoadDataListener(this);
    }

    @Override
    public void loadSuccess(String content) {
        if (content.indexOf("girl")>0){
            CateGirlBean girlBean = new Gson().fromJson(content, CateGirlBean.class);
            List<CateGirlBean.GirlBean> girl = girlBean.getGirl();
            if (girl != null) {
                list.clear();
                list.addAll(girl);
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
                if (lastPosition==position){
                    closeRight();

                }else{

                }
            }else{
                openRight();
            }
        }
    }


    class GirlAdapter extends BaseListAdapter<CateGirlBean.GirlBean> {

        public GirlAdapter(List<CateGirlBean.GirlBean> list) {
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
