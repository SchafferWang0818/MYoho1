package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import schaffer.myoho.Bean.ExpandDataBean;
import schaffer.myoho.Bean.ExpandDataChildBean;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/9/10.
 */
public class PayLocationExAdapter extends BaseExpandableListAdapter {
    List<ExpandDataBean> list;
    Context ctx;

    public PayLocationExAdapter(List<ExpandDataBean> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).childList.size();
    }

    @Override
    public ExpandDataBean getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public ExpandDataChildBean getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).childList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(ctx, R.layout.item_pay_location_type_exlv_parent, null);
        TextView tv = (TextView) convertView.findViewById(R.id.item_pay_location_ex_lv_par_tv);
        tv.setText(list.get(groupPosition).parentTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_pay_location_type_exlv_child, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.childCb.setChecked(list.get(groupPosition).childList.get(childPosition).isChecked);
        holder.childCb.setEnabled(false);
        holder.childTv.setText(list.get(groupPosition).childList.get(childPosition).childTitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {
        @InjectView(R.id.item_pay_location_ex_lv_child_tv)
        TextView childTv;
        @InjectView(R.id.item_pay_location_ex_lv_child_cb)
        CheckBox childCb;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
