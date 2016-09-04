package schaffer.myoho.DefinedView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;

import schaffer.myoho.Utils.MLog;

/**
 * Created by a7352 on 2016/8/31.
 */
public class GoodsScrollView extends ScrollView {


    private int y;
    private int height;
    private int child1Height;
    private View child1;
    private ViewGroup child;
    private boolean isUp;
    private boolean isDown;
    private View child2;
    private int child2Height;
    private boolean upScroll;
    private int x;
    private int scaledTouchSlop;
    private Activity a;


    public GoodsScrollView(Context context) {
        this(context, null);
    }

    public GoodsScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodsScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        a = (Activity) context;
        init();
    }

    private void init() {
        post(new Runnable() {

            @Override
            public void run() {
                ViewGroup parent = (ViewGroup) getParent();
                MLog.w("parent-->" + parent);
                height =/* parent.*/getHeight();
//                height = a.getWindowManager().getDefaultDisplay().getHeight();
                child = (ViewGroup) getChildAt(0);
                child1 = child.getChildAt(0);
                child1Height = child1.getHeight();
                MLog.w(height + "---" + child1Height);
                child2 = child.getChildAt(1);
                child2Height = child2.getHeight();
            }
        });
        ViewConfiguration config = ViewConfiguration.get(getContext());
        scaledTouchSlop = config.getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                x = (int) ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (ev.getRawY() - y);
                int moveX = (int) (ev.getRawX() - x);
                if (Math.abs(moveY) >=scaledTouchSlop && Math.abs(moveY) > Math.abs(moveX)) {
                    y = (int) ev.getRawY();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                y = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) (ev.getRawY() - y);

                //moveY<0说明向上拉,向上拉到上拉2/3屏幕的大小的时候,难拉效果
                //moveY>0说明向下拉,向下拉到下拉2/3屏幕的大小时候,难拉效果
                if (moveY < 0 && getScrollY() > child1Height - height && getScrollY() < child1Height - height / 4) {
                    MLog.w("上拉---->" + getScrollY());
                    isUp = true;
                    scrollTo(0, getScrollY() - moveY / 3);
                    y = (int) ev.getRawY();
                    return true;
                }
                if (moveY > 0 && getScrollY() < child1Height && getScrollY() > child1Height - height / 4) {
                    MLog.w("下拉---->");
                    isDown = true;
                    scrollTo(0, getScrollY() - moveY / 3);
                    y = (int) ev.getRawY();
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                if (isUp) {
                    isUp = false;
                    if (getScrollY() < child1Height - height * 3 / 4 && getScrollY() > child1Height - height) {
                        scrollTo(0, child1Height - height);
                        return true;
                    } else {
                        scrollTo(0, child1Height);
                        return true;
                    }
                }
                if (isDown) {
                    isDown = false;
                    if (getScrollY() < child1Height && getScrollY() > child1Height - height / 4) {
                        scrollTo(0, child1Height);
                        return true;
                    } else {
                        scrollTo(0, child1Height - height);
                        return true;
                    }
                }

                break;
        }

        return super.onTouchEvent(ev);
    }

    public void setScrollFootPosition() {
        scrollTo(0, child1Height - height);
    }

    @Override
    public void fling(int velocityY) {

        MLog.w("当前速度-->" + velocityY);
        if (velocityY > 0 && getScrollY() > 0 && getScrollY() < child1Height - height) {
            smoothScrollTo(0, child1Height - height);
//            super.fling(velocityY);
//        } else if (getScrollY() == child1Height - height/* || getScrollY() == child1Height*/) {
//            MLog.w("滚动到临界值");
//            super.fling(0);
        } else if (velocityY < 0 && getScrollY() > child1Height && getScrollY() < child2Height + child1Height - height) {
            smoothScrollTo(0, child1Height);
            MLog.w("当前位置--->" + getScrollY() + "-->" + (child2Height - height));
        }
//        else if (getScrollY() > child1Height - height / 2 && getScrollY() < child1Height + height / 2) {
//            MLog.w("需要滚动到具体位置");
//            if (upScroll) {
//                upScroll = false;
//                if (getScrollY() < child1Height - height / 4){
//                    smoothScrollTo(0,child1Height);
//                }else{
//                    smoothScrollTo(0, child1Height - height);
//                }
//            }
//        }
        else {
            if (velocityY < 0) {
                upScroll = true;
            }
            super.fling(velocityY);
        }


    }
}
