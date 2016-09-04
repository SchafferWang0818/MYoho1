package schaffer.myoho.DefinedView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by a7352 on 2016/8/29.
 */
public class DGridView extends GridView {
    public DGridView(Context context) {
        super(context);
    }

    public DGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
