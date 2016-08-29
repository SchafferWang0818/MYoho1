package schaffer.myoho.DefinedView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RadioButton;

import schaffer.myoho.R;
import schaffer.myoho.Utils.DeminUtils;

/**
 * Created by a7352 on 2016/8/24.
 */
public class RadioView extends RadioButton {


    private int num;
    private int numColor;
    private int width;
    private int radius;
    private Paint textPaint;
    private Paint dotPaint;
    private boolean showDot;
    private String text;

    public RadioView(Context context) {
        this(context, null);
    }

    public RadioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadioView);
        num = array.getInteger(R.styleable.RadioView_number, 0);
        numColor = array.getColor(R.styleable.RadioView_numColor, Color.parseColor("#ffffff"));
        showDot = array.getBoolean(R.styleable.RadioView_showDot, false);
        array.recycle();
        initPaint();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        width = getMeasuredWidth();
        radius = width / 8;
    }

    private void initPaint() {
        dotPaint = new Paint();
        dotPaint.setColor(Color.parseColor("#ff0000"));
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DeminUtils.sp2px(10));
        textPaint.setColor(numColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num > 0 && showDot) {
            drawDot(canvas);
        }
    }

    private void drawDot(Canvas canvas) {
        float x_co = width * 7 / 8.0f;
        float y_co = getMeasuredHeight() / 8.0f;
        canvas.drawCircle(x_co, y_co, radius, dotPaint);

        Rect rect = new Rect();
        text = num + "";
        float v = textPaint.measureText(text, 0, text.length());
        textPaint.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text, x_co - v / 2, y_co + rect.height() / 2, textPaint);
    }
}
