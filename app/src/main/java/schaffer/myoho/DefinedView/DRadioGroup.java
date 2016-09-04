package schaffer.myoho.DefinedView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by a7352 on 2016/8/22.
 */
public class DRadioGroup extends LinearLayout {


    private int childCount;

    public DRadioGroup(Context context) {
        this(context, null);
    }

    public DRadioGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DRadioGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        childCount = getChildCount();
        int freeHeight = getMeasuredHeight() - contentHeight();
        int marginTop = 0;
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).layout(0, marginTop, getChildAt(i).getMeasuredWidth(), marginTop + getChildAt(0).getMeasuredHeight());
            int bottom = getChildAt(i).getBottom();
            marginTop = bottom + freeHeight / childCount-1;
        }

    }

    private int contentHeight() {
        int contentHeight = 0;
        for (int i = 0; i < childCount; i++) {
            int measuredHeight = getChildAt(i).getMeasuredHeight();
            contentHeight += measuredHeight;
        }
        return contentHeight;
    }


}
