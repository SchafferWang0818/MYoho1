package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import schaffer.myoho.Bean.FollowGoodsBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.PathUtils;

/**
 * Created by a7352 on 2016/8/31.
 */
public class SortBrandGvAdapter extends BaseListAdapter<FollowGoodsBean.FollowBean.GoodsBean> {

    public SortBrandGvAdapter(List<FollowGoodsBean.FollowBean.GoodsBean> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_brand_sort_gv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemBrandSortInfoTv.setText("Good--->" + position);
        holder.itemBrandSortPriceTv.setText(list.get(position).getPrice());
        Picasso.with(context).load(PathUtils.IMG_HEAD + list.get(position).getGoodsimg()).placeholder(R.drawable.product_icon_loading_default).fit().into(holder.itemBrandSortIv);
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.item_brand_sort_iv)
        ImageView itemBrandSortIv;
        @InjectView(R.id.item_brand_sort_info_tv)
        TextView itemBrandSortInfoTv;
        @InjectView(R.id.item_brand_sort_price_tv)
        TextView itemBrandSortPriceTv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
