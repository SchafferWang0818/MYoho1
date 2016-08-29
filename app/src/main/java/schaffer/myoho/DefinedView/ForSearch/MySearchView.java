package schaffer.myoho.DefinedView.ForSearch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Bean.AllBrandBean;
import schaffer.myoho.Event.SecondSwitchEvent;
import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/26.
 */
public class MySearchView extends BaseView {


    private View inflateView;
    private EditText edt;
    private Button cancel;
    private LinearLayout historyGroup;
    private TagFlowLayout historyflow;
    private TagFlowLayout recommendflow;
    private List<String> history;
    private AllBrandBean allBrand;
    private PopupWindow popupWindow;

    public MySearchView(Context ctx) {
        super(ctx);
        history = new ArrayList<>();
    }

    @Override
    public void initData(Context ctx) {
        inflateView = View.inflate(ctx, R.layout.view_search_flow_lv, null);
        edt = (EditText) inflateView.findViewById(R.id.view_search_edt);
        cancel = (Button) inflateView.findViewById(R.id.view_search_cancel_btn);
        historyGroup = (LinearLayout) inflateView.findViewById(R.id.view_search_history_group);
        historyflow = (TagFlowLayout) inflateView.findViewById(R.id.view_search_history);
        recommendflow = (TagFlowLayout) inflateView.findViewById(R.id.view_search_recommend);
        final ListView searchLv = new ListView(ctx);
        searchLv.setBackgroundColor(Color.WHITE);
        edt.post(new Runnable() {
            @Override
            public void run() {
                int height = inflateView.getHeight();
                popupWindow = new PopupWindow(searchLv, inflateView.getWidth(), height, false);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
            }
        });

    }

    public void setAllBrand(AllBrandBean allBrand){
     this.allBrand = allBrand;
    }
    @Override
    public void initAdapter() {
//        historyflow.setAdapter(new TagAdapter<String>(history) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                View view = View.inflate(ctx, R.layout.item_search_flow, null);
//                TextView tv = (TextView) view.findViewById(R.id.item_search_flow_tv);
//                tv.setText(s);
//                return view;
//            }
//        });

        List<String> list = new ArrayList<>();
        list.add("nike");
        list.add("5CM");
        list.add("STAYREAL");

        recommendflow.setAdapter(new TagAdapter<String>(history) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = View.inflate(ctx, R.layout.item_search_flow, null);
                TextView tv = (TextView) view.findViewById(R.id.item_search_flow_tv);
                tv.setText(s);
                return view;
            }
        });

    }

    @Override
    public void initListener() {
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<AllBrandBean.BrandBean> brand = allBrand.getBrand();
                List<String> temp = new ArrayList<String>();
                for (AllBrandBean.BrandBean brandBean : brand) {
                    if (brandBean.getName().contains(s)){
                        temp.add(brandBean.getName());
                    }

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SecondSwitchEvent(0));
            }
        });

    }
}
