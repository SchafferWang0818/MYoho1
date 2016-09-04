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
import schaffer.myoho.Utils.DeminUtils;
import schaffer.myoho.Utils.MLog;

/**
 * Created by a7352 on 2016/9/4.
 */
public class DTabView extends RelativeLayout {


    private String text;
    private float textSize;
    private int textColor;
    private ColorStateList colorStateList;
    private int resourceId;
    private ImageView iv;
    private TextView tv;

    public DTabView(Context context) {
        this(context, null);
    }

    public DTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DTabView);
        text = (String) array.getText(R.styleable.DTabView_text);
        textSize = array.getDimension(R.styleable.DTabView_textSize, 15);
        textColor = array.getColor(R.styleable.DTabView_textColor, Color.BLACK);
        colorStateList = array.getColorStateList(R.styleable.DTabView_textColor);
        resourceId = array.getResourceId(R.styleable.DTabView_draw, R.mipmap.ic_launcher);
        init();
    }

    private void init() {
        LinearLayout group = new LinearLayout(getContext());
        group.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams groupParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        groupParams.addRule(CENTER_IN_PARENT);
        group.setLayoutParams(groupParams);

        tv = new TextView(getContext());
        tv.setText(text);
        tv.setTextSize(textSize);
        if (colorStateList != null) {
            tv.setTextColor(colorStateList);
        } else {
            tv.setTextColor(textColor);
        }
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0, 0, DeminUtils.dp2px(20), 0);
        textParams.gravity = Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(textParams);

        iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(resourceId);
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DeminUtils.dp2px(20), DeminUtils.dp2px(30));
        ivParams.gravity = Gravity.CENTER_VERTICAL;
        iv.setLayoutParams(ivParams);

        group.addView(tv);
        group.addView(iv);

        addView(group);
    }

    public void setImageRes(int resourceId) {
        iv.setImageResource(resourceId);
    }

    /**
     * 四种状态,未选中,选中,选中向上,选中向下
     * 图片的状态分为四种,可以归结为selected和pressed.
     * 当前控件
     * 被选中时默认selected unpressed;
     * 再次点击时,设置为selected,pressed;
     * 选中其他控件,该控件为unselected,press状态由上一次决定
     */
    public boolean isSelected;
    public boolean isPressed;

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        isSelected = true;
        MLog.w("点击事件");
        setImageRes(R.drawable.selector_mytab_draw);
        if (isPressed) {
            iv.setPressed(false);
            isPressed = false;
            if (onPressChangStateListener != null) {
                onPressChangStateListener.reverse(l);
            }
        } else {
            iv.setPressed(true);
            isPressed = true;
            if (onPressChangStateListener != null) {
                onPressChangStateListener.sequence(l);
            }
        }
    }

    public interface OnPressChangStateListener {
        /**
         * 顺序
         */
        void sequence(Object o);

        /**
         * 逆序
         */
        void reverse(Object o);
    }

    OnPressChangStateListener onPressChangStateListener;

    public void setOnPressChangStateListener(OnPressChangStateListener listener) {
        this.onPressChangStateListener = listener;
    }


}
