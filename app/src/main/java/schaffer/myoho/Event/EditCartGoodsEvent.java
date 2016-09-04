package schaffer.myoho.Event;

/**
 * Created by a7352 on 2016/9/3.
 */
public class EditCartGoodsEvent {
    public int type;
    public static final int DISABLE = 0;
    public static final int ENABLE = 1;

    public EditCartGoodsEvent(int type) {
        this.type = type;
    }
}
