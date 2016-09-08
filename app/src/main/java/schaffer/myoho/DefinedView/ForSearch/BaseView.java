package schaffer.myoho.DefinedView.ForSearch;

import android.content.Context;

/**
 * Created by a7352 on 2016/8/26.
 */
public abstract class BaseView {
    public Context ctx;

    public BaseView(Context ctx) {
        this.ctx = ctx;
    }

    public abstract void initData(Context ctx);

    public abstract void initAdapter();

    public abstract void initListener();

    public void loadData() {

    }

    public void init(Context ctx) {
        initData(ctx);
        initAdapter();
        initListener();
    }


}
