package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import schaffer.myoho.Bean.AllBrandLetterBean;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/25.
 */
public class MExAdapter extends BaseExAdapter<AllBrandLetterBean> {


    public MExAdapter(List<AllBrandLetterBean> list, Context ctx) {
        super(list, ctx);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder holder = null;
        if (convertView == null) {
            holder = new ParentViewHolder();
            convertView = View.inflate(ctx, R.layout.item_second_exlv_parent, null);
            holder.tv = (TextView) convertView.findViewById(R.id.item_second_exlv_parent_tv);
            convertView.setTag(holder);
        } else {
            holder = (ParentViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(groupPosition).letterTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_second_exlv_child, null);
            holder = new ChildViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.item_second_exlv_child_tv);
            holder.iv = (ImageView) convertView.findViewById(R.id.item_second_exlv_child_hotiv);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(groupPosition).list.get(childPosition).getName());

        if (list.get(groupPosition).list.get(childPosition).getHotflag().equals("1")) {
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.iv.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }


    class ParentViewHolder {
        TextView tv;
    }

    class ChildViewHolder {
        TextView tv;
        ImageView iv;
    }
}
