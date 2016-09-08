package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import schaffer.myoho.Bean.RoamBean;
import schaffer.myoho.R;
import schaffer.myoho.Utils.MLog;

/**
 * Created by a7352 on 2016/9/5.
 */
public class RoamChildlvAdapter extends BaseListAdapter<RoamBean.DataBean.ListBean.ArtListBean> {
    public RoamChildlvAdapter(List<RoamBean.DataBean.ListBean.ArtListBean> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_roam_child_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RoamBean.DataBean.ListBean.ArtListBean art = list.get(position);
        String substring = art.getSrc().substring(0, art.getSrc().indexOf("?"));
        MLog.w("截取的网络地址是:" + substring);
        Picasso.with(context).load(art.getAuthor().getAvatar()).placeholder(R.mipmap.ic_launcher).into(holder.headIv);
        Picasso.with(context).load(substring).placeholder(R.drawable.product_icon_loading_default).into(holder.iv);
        holder.nameTv.setText(art.getAuthor().getName());
        holder.titleTv.setText(art.getTitle());
        holder.contentTv.setText(art.getIntro());
        holder.timeTv.setText(art.getPublish_time());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.item_roam_child_lv_head_iv)
        CircleImageView headIv;
        @InjectView(R.id.item_roam_child_lv_name_tv)
        TextView nameTv;
        @InjectView(R.id.item_roam_child_lv_iv)
        ImageView iv;
        @InjectView(R.id.item_roam_lv_title_tv)
        TextView titleTv;
        @InjectView(R.id.item_roam_lv_content_tv)
        TextView contentTv;
        @InjectView(R.id.item_roam_lv_time_tv)
        TextView timeTv;
        @InjectView(R.id.item_roam_child_lv_share)
        ImageButton share;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
