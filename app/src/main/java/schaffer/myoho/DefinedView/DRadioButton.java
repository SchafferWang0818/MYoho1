package schaffer.myoho.DefinedView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import schaffer.myoho.R;

/**
 * Created by a7352 on 2016/9/4.
 */
public class DRadioButton extends RadioButton {


    private boolean show;
    private int num;
    private int color;
    private Paint circlePaint;
    private Paint numPaint;
    private int height;
    private int width;

    public DRadioButton(Context context) {
        this(context, null);
    }

    public DRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DRadioButton);
        color = typedArray.getColor(R.styleable.DRadioButton_numColor, Color.WHITE);
        num = typedArray.getInteger(R.styleable.DRadioButton_num, 0);
        show = typedArray.getBoolean(R.styleable.DRadioButton_showDot, false);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.RED);

        numPaint = new Paint();
        numPaint.setAntiAlias(true);
        numPaint.setStyle(Paint.Style.FILL);
        numPaint.setTextSize(sp2px(20));
        numPaint.setColor(color);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.w("TAG", "left" + left + ",top" + top + ",right" + right + ",bottom" + bottom);
        this.height = bottom - top;
        this.width = right - left;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制的圆是占全部的四分之一
        //半径就是当前宽高度的最小值的四分之一
        //位置为右上角3/4的宽和1/4的高处
        int radius = Math.min(height, width) / 4;
        int x_co = (int) (width * 3 / 4.0f);
        int y_co = (int) (height / 4.0f);
        if (num > 0 && show) {
            canvas.drawCircle(x_co, y_co, radius, circlePaint);
            canvas.save();
            canvas.restore();

            String text = num + "";
            float measureResult = numPaint.measureText(text, 0, text.length());

            Rect textRect = new Rect();
            numPaint.getTextBounds(text, 0, text.length(), textRect);
            int x = (int) (-measureResult/ 2 + x_co);
            int y = textRect.height() / 2 + y_co;
            canvas.drawText(text, x, y, numPaint);
        }


    }

    public int sp2px(int spValue) {
        return (int) (getResources().getDisplayMetrics().scaledDensity * spValue);
    }

    public void setShow(boolean show, int num) {
        this.show = show;
        this.num = num;
    }


}
