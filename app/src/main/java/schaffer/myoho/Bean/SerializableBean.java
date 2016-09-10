package schaffer.myoho.Bean;

import java.io.Serializable;

/**
 * Created by a7352 on 2016/9/9.
 */
public class SerializableBean implements Serializable {
    public Object object;

    public SerializableBean(Object object) {
        this.object = object;
    }
}
