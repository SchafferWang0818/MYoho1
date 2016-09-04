package schaffer.myoho.DefinedView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by a7352 on 2016/8/31.
 */
public class DListView extends ListView {

    public DListView(Context context) {
        super(context);
    }

    public DListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
