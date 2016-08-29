package schaffer.myoho.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import schaffer.myoho.Bean.HomeGvHeaderBean;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/29.
 */
public class HomeGvHeaderAdapter extends BaseListAdapter<HomeGvHeaderBean> {

    List<HomeGvHeaderBean> list;

    public HomeGvHeaderAdapter(List<HomeGvHeaderBean> list, Context ctx) {
        super(list);
        this.list = list;
        this.context = ctx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(context, R.layout.item_head_lv_header_gv, null);
        TextView title = (TextView) convertView.findViewById(R.id.item_home_lv_header_gv_titletv);
        ImageView icon = (ImageView) convertView.findViewById(R.id.item_home_lv_header_gv_iconiv);
        title.setText(list.get(position).title);
        icon.setImageResource(list.get(position).imgRes);
        return convertView;
    }


}
