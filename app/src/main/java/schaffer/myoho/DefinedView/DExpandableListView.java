package schaffer.myoho.DefinedView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by a7352 on 2016/9/10.
 */
public class DExpandableListView extends ExpandableListView {


    public DExpandableListView(Context context) {
        super(context);
    }

    public DExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
