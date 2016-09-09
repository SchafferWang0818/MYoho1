package schaffer.myoho.FragmentSort;

import java.util.Collections;
import java.util.Comparator;

import schaffer.myoho.Bean.FollowGoodsBean;

/**
 * Created by a7352 on 2016/8/30.
 */
public class FragmentSortChild2 extends SortChildBaseFragment {


    @Override
    protected void initDatas() {

    }

    //升序
    @Override
    public void ascendingOrder() {
        super.ascendingOrder();
        Collections.sort(goodsBeanList, new Comparator<FollowGoodsBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FollowGoodsBean.FollowBean.GoodsBean lhs, FollowGoodsBean.FollowBean.GoodsBean rhs) {
                return Float.compare(Float.parseFloat(lhs.getPrice()), Float.parseFloat(rhs.getPrice()));
            }
        });
        adapter.notifyDataSetChanged();
    }

    //降序
    @Override
    public void descendingOrder() {
        super.descendingOrder();
        Collections.sort(goodsBeanList, new Comparator<FollowGoodsBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FollowGoodsBean.FollowBean.GoodsBean lhs, FollowGoodsBean.FollowBean.GoodsBean rhs) {
                return Float.compare(Float.parseFloat(rhs.getPrice()), Float.parseFloat(lhs.getPrice()));
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sort() {
        super.sort();
    }
}
