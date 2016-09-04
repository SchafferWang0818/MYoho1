package schaffer.myoho.Event;

import java.util.List;

/**
 * Created by a7352 on 2016/9/3.
 */
public class AllCheckEvent {
    public boolean checked;
    public List<Integer>checks;
    public AllCheckEvent(boolean checked) {
        this.checked = checked;
    }

    public AllCheckEvent(boolean checked, List<Integer> checks) {
        this.checked = checked;
        this.checks = checks;
    }
}
