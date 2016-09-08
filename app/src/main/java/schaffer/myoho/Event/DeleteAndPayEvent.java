package schaffer.myoho.Event;

/**
 * Created by a7352 on 2016/9/6.
 */
public class DeleteAndPayEvent {
    public boolean state;
    public static final boolean PAY_STATE = true;
    public static final boolean DELETE_STATE = false;

    public DeleteAndPayEvent(boolean state) {
        this.state = state;
    }
}
