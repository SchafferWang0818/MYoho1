package schaffer.myoho.Adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by a7352 on 2016/8/24.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    List<T> list;
    public Context context;


    public BaseListAdapter(List<T> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
