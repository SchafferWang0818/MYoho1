package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import schaffer.myoho.Bean.HomeBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/30.
 */
public class HomeLvAdapter extends BaseListAdapter<List<HomeBean.BrandBean>> {

    private static List<ImageView> ivList;

    public HomeLvAdapter(List<List<HomeBean.BrandBean>> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_home_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String name = list.get(position).get(0).getName();
        holder.TitleTv.setText(name);
        for (int i = 0; i < ivList.size(); i++) {
            String imgpath = list.get(position).get(i).getImgpath();
            Picasso.with(context).load(PathUtils.IMG_HEAD + imgpath).placeholder(R.drawable.product_icon_loading_default).into(ivList.get(i));
        }
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.item_home_lv_titleTv)
        TextView TitleTv;
        @InjectView(R.id.item_home_lv_iv1)
        ImageView iv1;
        @InjectView(R.id.item_home_lv_iv2)
        ImageView iv2;
        @InjectView(R.id.item_home_lv_iv3)
        ImageView iv3;
        @InjectView(R.id.item_home_lv_iv4)
        ImageView iv4;
        @InjectView(R.id.item_home_lv_iv5)
        ImageView iv5;
        @InjectView(R.id.item_home_lv_iv6)
        ImageView iv6;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            ivList = new ArrayList<>();
            ivList.add(iv1);
            ivList.add(iv2);
            ivList.add(iv3);
            ivList.add(iv4);
            ivList.add(iv5);
            ivList.add(iv6);
        }
    }
}
