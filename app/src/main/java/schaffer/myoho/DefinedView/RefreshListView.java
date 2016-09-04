package schaffer.myoho.DefinedView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/8/29.
 */
public class RefreshListView extends RelativeLayout {


    private View header;
    private View footer;
    private ListView lv;
    private LayoutParams headerParams;
//    private ArrayAdapter<String> adapter;
    private List<String> list;
    private LayoutParams lvParams;
    private int headerHeight;
    private int footerHeight;
    private LayoutParams footerParams;
    private int y;
    public TextView headTv;
    public ProgressBar headPb;
    public TextView footTv;
    //    public ProgressBar footPb;
    private int moveY;
    private boolean isLoadTop;
    private boolean isLoadFoot;

    public RefreshListView(Context context) {
        this(context, null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //用于显示加载数据的头部
        header = View.inflate(getContext(), R.layout.header_home_refresh_listview, null);
        headTv = (TextView) header.findViewById(R.id.header_home_lv_tv);
        headPb = (ProgressBar) header.findViewById(R.id.header_home_lv_pb);
        headerParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerParams.addRule(ALIGN_PARENT_TOP);
        header.setLayoutParams(headerParams);
        header.setId(R.id.header);
        //用于显示加载数据的尾部
        footer = View.inflate(getContext(), R.layout.footer_home_defined_view_lv, null);
        footTv = (TextView) footer.findViewById(R.id.footer_home_lv_tv);
//        footPb = (ProgressBar) header.findViewById(R.id.footer_home_lv_pb);
        footerParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerParams.addRule(ALIGN_PARENT_BOTTOM);
        footer.setLayoutParams(footerParams);
        footer.setId(R.id.footer);
        lv = new ListView(getContext());
        lvParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lvParams.addRule(BELOW, R.id.header);
        lvParams.addRule(ABOVE, R.id.footer);
        lv.setLayoutParams(lvParams);
//        initLvData_Adapter();
        addView(header);
        addView(footer);
        addView(lv);
    }

//    private void initLvData_Adapter() {
//        list = new ArrayList<>();
//        for (int i = 0; i < 40; i++) {
//            list.add("item---->" + i);
//        }
//        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
//        lv.setAdapter(adapter);
//    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        headerHeight = header.getMeasuredHeight();
        footerHeight = footer.getMeasuredHeight();
//        MLog.w("headerHeight:" + headerHeight);
//        MLog.w("footerHeight:" + footerHeight);
        headerParams.topMargin = -headerHeight;
        header.setLayoutParams(headerParams);
        footerParams.bottomMargin = -footerHeight;
        footer.setLayoutParams(footerParams);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        MLog.w(lv.getTop() + "," + lv.getBottom());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                if (isLoadTop || isLoadFoot) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) (ev.getRawY() - y);
                //下拉刷新,要求第一个条目的getTop = 0
//                MLog.w("lv.getChildAt(0).getTop() == 0?" + lv.getChildAt(0).getTop());
                if (moveY > 0 && lv.getFirstVisiblePosition() == 0) {
//                    MLog.w("拦截下拉");
                    if (lv.getChildCount() == 0) {
                        return false;
                    } else {
                        if (lv.getChildAt(0).getTop() == 0) {
                            y = (int) ev.getRawY();
                            return true;
                        }
                    }
                    return false;
                }
                //上拉刷新,要求最后一个可见条目为最后一条数据,并且getBottom为屏幕的高度
//                MLog.w(adapter.getCount() + "<-相等否->" + list.size());

                if (moveY < 0 && lv.getLastVisiblePosition() == lv.getAdapter().getCount() - 1) {
//                    MLog.w("拦截上拉");
                    int i = lv.getLastVisiblePosition() - lv.getFirstVisiblePosition() - 1;
                    int bottom = lv.getChildAt(i).getBottom();

//                    MLog.w("bottom--->" + bottom);
//                    MLog.w("lv.getHeight()--->" + lv.getHeight());
                    if (bottom <= lv.getHeight()) {
                        return true;
                    }
                }

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moveY = (int) (event.getRawY() - y) / 3;
                swipeDown();
                swipeUp();
                break;
            case MotionEvent.ACTION_UP:
                upAtTop();
                upAtBottom();
                break;


        }
        return true;
    }

    //底部放手
    private void upAtBottom() {
        if (footerParams.bottomMargin < 0) {
            setInitialBottom();
        } else {
            footerParams.bottomMargin = 0;
            footer.setLayoutParams(footerParams);
            lvParams.topMargin = footerHeight;
            lv.setLayoutParams(lvParams);
            isLoadFoot = true;
            footTv.setText("正在加载....");
            if (onRefreshListener != null) {
                onRefreshListener.loadbottom();
            }
        }
    }

    //顶部放手
    private void upAtTop() {
        if (headerParams.topMargin < 0) {
            initTop();
        } else {
            headerParams.topMargin = 0;
            header.setLayoutParams(headerParams);
            headTv.setText("正在加载...");
            headPb.setVisibility(VISIBLE);
            isLoadTop = true;
            if (onRefreshListener != null) {
                onRefreshListener.loadTop();
            }
        }
    }

    private void initTop() {
        headerParams.topMargin = -headerHeight;
        header.setLayoutParams(headerParams);
        isLoadTop = false;
    }

    //底部
    private void swipeUp() {
        if (isLoadFoot) return;
        //上拉刷新,要求最后一个可见条目为最后一条数据,并且getBottom为屏幕的高度
        if (moveY < 0 && lv.getLastVisiblePosition() == lv.getAdapter().getCount() - 1) {
            int move = -footerHeight - moveY;
            footerParams.bottomMargin = move;
            lvParams.topMargin = moveY;
            footer.setLayoutParams(footerParams);
            lv.setLayoutParams(lvParams);
        }
    }

    //顶部
    private void swipeDown() {
        if (isLoadTop) return;
        //向下移动
        if (moveY > 0 && lv.getFirstVisiblePosition() == 0) {
            int move = -headerHeight + moveY;
//            int realMove = Math.min(Math.max(move, -headerHeight), headerHeight / 2);
            headerParams.topMargin = move;
            header.setLayoutParams(headerParams);
        }
    }


    public void setAdapter(BaseAdapter adapter) {
        lv.setAdapter(adapter);
    }

    public interface OnRefreshListener {
        void loadTop();

        void loadbottom();
    }

    OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void addHeadView(View view) {
        lv.addHeaderView(view);
    }

    public void addFootView(View view) {
        lv.addFooterView(view);
    }

    public void setInitialTop() {
        headPb.setVisibility(GONE);
        headTv.setText("下拉加载");
        headerParams.topMargin = -headerHeight;
        header.setLayoutParams(headerParams);
        isLoadTop = false;
    }

    public void setInitialBottom() {
        footerParams.bottomMargin = -footerHeight;
        lvParams.topMargin = 0;
        footTv.setText("上拉加载");
        footer.setLayoutParams(footerParams);
        lv.setLayoutParams(lvParams);
        isLoadFoot = false;

    }


}
