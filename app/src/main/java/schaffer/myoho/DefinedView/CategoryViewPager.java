package schaffer.myoho.DefinedView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by a7352 on 2016/8/23.
 */
public class CategoryViewPager extends ViewPager {


    public CategoryViewPager(Context context) {
        super(context);
    }

    public CategoryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
