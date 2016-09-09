package schaffer.myoho.DefinedView;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;

public class SortGridView extends RelativeLayout {


    private LayoutParams headParams;
    private TextView headtv;
    private TextView foottv;
    private LayoutParams footParams;
    private GridView gv;
    private LayoutParams gvParams;
    private int headMeasuredHeight;
    private int footMeasuredHeight;
    private int rawY;
    private int moveY;
//    private BaseAdapter adapter;

    public SortGridView(Context context) {
        this(context, null);
    }

    public SortGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        headtv = new TextView(getContext());
        headParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(30));
        headtv.setBackgroundColor(Color.parseColor("#00ff00"));
        headtv.setText("下拉刷新");
        headtv.setId(R.id.infoHeader);
        headParams.addRule(CENTER_HORIZONTAL);
        headtv.setLayoutParams(headParams);

        foottv = new TextView(getContext());
        foottv.setText("上拉加载");
        foottv.setBackgroundColor(Color.parseColor("#00ff00"));
        footParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DeminUtils.dp2px(30));
        footParams.addRule(CENTER_HORIZONTAL);
        footParams.addRule(ALIGN_PARENT_BOTTOM);
        foottv.setId(R.id.infoFooter);
        foottv.setLayoutParams(footParams);


        gv = new GridView(getContext());
        gv.setNumColumns(2);
//        gv.setBackgroundColor(Color.parseColor("#0000f0"));
        gvParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        gvParams.addRule(BELOW, R.id.infoHeader);
        gvParams.addRule(ABOVE, R.id.infoFooter);
        gv.setLayoutParams(gvParams);

        //设置位置
        headMeasuredHeight = headParams.height;
        headParams.topMargin = -headMeasuredHeight;
        headtv.setLayoutParams(headParams);
        footMeasuredHeight = footParams.height;
        footParams.bottomMargin = -footMeasuredHeight;
        foottv.setLayoutParams(headParams);
        addView(headtv);
        addView(foottv);
        addView(gv);
    }

//    public void setArrayAdapter(BaseAdapter arrayAdapter) {
//        gv.setAdapter(arrayAdapter);
//    }

    public void setGvAdapter(BaseAdapter gvAdapter) {
        gv.setAdapter(gvAdapter);
//        this.adapter = gvAdapter;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

    }

    boolean isLoadHead = false;
    boolean isLoadFoot = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = (int) event.getRawY();
                if (isLoadFoot || isLoadHead) return true;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (event.getRawY() - rawY);
//                if (gv.getChildCount() == 0) return true;
                if ( moveY > 0 && gv.getFirstVisiblePosition() == 0&& gv.getChildAt(0).getTop() == 0) {
                    //向下滑,顶部下拉加载
                    rawY = (int) event.getRawY();
                    return true;
                }
                if (moveY < 0 && gv.getLastVisiblePosition() == gv.getAdapter().getCount()-1&& gv.getChildAt(gv.getLastVisiblePosition() - gv.getFirstVisiblePosition()).getBottom() == gv.getHeight()) {
                    //向上滑,底部上拉加载
                    rawY = (int) event.getRawY();
                    return true;
                }
                break;


        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                moveY = (int) (event.getRawY() - rawY);
                moveHead();
                moveFoot();
                break;
            case MotionEvent.ACTION_UP:
                upFingerHead();
                upFingerFoot();
                break;

        }
        return true;
    }

    private void upFingerFoot() {
        if (footParams.bottomMargin > 0) {
            footParams.bottomMargin = 0;
            foottv.setLayoutParams(footParams);
            gvParams.topMargin = -footMeasuredHeight;
            gv.setLayoutParams(gvParams);
            foottv.setText("正在加载....");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (onLoadListener != null) {
                        isLoadFoot = true;
                        onLoadListener.loadFoot();
                    }
                }
            }, 1500);
        } else if (footParams.bottomMargin > -footMeasuredHeight && footParams.bottomMargin <= 0) {
            initFootState();
        }
    }

    public void initFootState() {
        foottv.setText("上拉加载");
        footParams.bottomMargin = -footMeasuredHeight;
        foottv.setLayoutParams(footParams);
        gvParams.topMargin = 0;
        gv.setLayoutParams(gvParams);
        gv.smoothScrollToPosition(gv.getAdapter().getCount() - 1);
        isLoadFoot = false;
    }

    private void moveFoot() {
        if (isLoadFoot) return;
        if (moveY < 0 && gv.getLastVisiblePosition() == gv.getAdapter().getCount() - 1) {
            footParams.bottomMargin = -footMeasuredHeight - moveY/3;
            gvParams.topMargin = moveY/3;
            foottv.setLayoutParams(footParams);
            gv.setLayoutParams(gvParams);
        }

    }

    private void upFingerHead() {
        int topMargin = headParams.topMargin;
        if (topMargin > 0) {
            headParams.topMargin = 0;
            headtv.setText("正在加载....");
            headtv.setLayoutParams(headParams);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (onLoadListener != null) {
                        isLoadHead = true;
                        onLoadListener.loadHead();
                    }
                }
            }, 1500);
        } else if (topMargin < 0 && topMargin > -headMeasuredHeight) {
            initHeadState();
        }
    }

    public void initHeadState() {
        headParams.topMargin = -headMeasuredHeight;
        headtv.setText("下拉加载");
        headtv.setLayoutParams(headParams);
        isLoadHead = false;
    }

    private void moveHead() {
        if (isLoadHead) return;
        if (moveY > 0 && gv.getFirstVisiblePosition() == 0) {
            headParams.topMargin = -headMeasuredHeight + moveY / 3;
            headtv.setLayoutParams(headParams);
        }
    }


    public interface OnLoadListener {
        void loadHead();

        void loadFoot();
    }

    OnLoadListener onLoadListener;

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
}
