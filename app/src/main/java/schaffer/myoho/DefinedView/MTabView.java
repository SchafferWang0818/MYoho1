package schaffer.myoho.DefinedView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import schaffer.myoho.R;

/**
 * 四种状态,未选中,选中,选中向上,选中向下
 * 图片的状态分为四种,可以归结为selected和pressed.
 * 当前控件
 * 被选中时默认selected unpressed;
 * 再次点击时,设置为selected,pressed;
 * 选中其他控件,该控件为unselected,press状态由上一次决定
 */
public class MTabView extends RelativeLayout {


    private TextView tv;
    private ImageView iv;
    private LinearLayout linearLayout;
    private String text;
    private float textSize;
    private int color;
    public ColorStateList colorStateList;
    private int resourceId;

    public MTabView(Context context) {
        this(context, null);
    }

    public MTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DTabView);
        text = (String) array.getText(R.styleable.DTabView_text);
        textSize = array.getDimension(R.styleable.DTabView_textSize, 15);
        color = array.getColor(R.styleable.DTabView_textColor, Color.BLACK);
        colorStateList = array.getColorStateList(R.styleable.DTabView_textColor);
        resourceId = array.getResourceId(R.styleable.DTabView_draw, R.mipmap.ic_launcher);
        initChild();
    }

    private void initChild() {
        initViews();

    }

    private void initViews() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        tv = new TextView(getContext());
        tv.setText(text);
        tv.setTextSize(textSize);

        if (colorStateList != null) {
            tv.setHintTextColor(colorStateList);
        } else {
            tv.setTextColor(color);
        }
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.gravity = Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(tvParams);

        linearLayout.addView(tv);
        iv = new ImageView(getContext());
        setImgBackgroundRes(resourceId);
//        MarginLayoutParams ivParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ivParams.leftMargin = DeminUtils.dp2px(20);
//        iv.setLayoutParams(ivParams);
        LinearLayout.LayoutParams ivParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ivParams1.gravity = Gravity.CENTER_VERTICAL;
        iv.setLayoutParams(ivParams1);
        linearLayout.addView(iv);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
    }

    public void setText(String content) {
        tv.setText(content);
    }

    public void setTextSize(float size) {
        tv.setTextSize(size);
    }


    public void setImgBackgroundRes(int resId) {
        iv.setBackgroundResource(resId);
    }

    public void setSelected(boolean isSelected) {
        tv.setSelected(isSelected);
        //当前控件被选中时,判断图片是否被选中
        iv.setPressed(isPressed);

    }

    boolean isPressed = false;

    /**
     * 前提是当前控件被selected,改变当前iv的状态
     */
    public void changedPressedState() {
        tv.setSelected(true);
        iv.setPressed(!isPressed);
    }


}
