package schaffer.myoho.Adapter;

import android.content.Context;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by a7352 on 2016/8/25.
 */
public abstract class BaseExAdapter<t> extends BaseExpandableListAdapter {
//    ExpandableListView
    List<t> list;
    Context ctx;

    public BaseExAdapter(List<t> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }


    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
