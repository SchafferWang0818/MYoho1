package schaffer.myoho.FragmentSort;

import java.util.Collections;
import java.util.Comparator;

import schaffer.myoho.Bean.FollowGoodsBean;

/**
 * Created by a7352 on 2016/8/30.
 */
public class FragmentSortChild3 extends SortChildBaseFragment {


    @Override
    protected void initDatas() {

    }

    @Override
    public void ascendingOrder() {
        super.ascendingOrder();
        Collections.sort(goodsBeanList, new Comparator<FollowGoodsBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FollowGoodsBean.FollowBean.GoodsBean lhs, FollowGoodsBean.FollowBean.GoodsBean rhs) {
                float lhsZheKou = 1 - Float.parseFloat(lhs.getDistance()) / Float.parseFloat(lhs.getPrice());
                float rhsZheKou = 1 - Float.parseFloat(rhs.getDistance()) / Float.parseFloat(rhs.getPrice());
                return Float.compare(lhsZheKou, rhsZheKou);
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void descendingOrder() {
        super.descendingOrder();
        Collections.sort(goodsBeanList, new Comparator<FollowGoodsBean.FollowBean.GoodsBean>() {
            @Override
            public int compare(FollowGoodsBean.FollowBean.GoodsBean lhs, FollowGoodsBean.FollowBean.GoodsBean rhs) {
                float lhsZheKou = 1 - Float.parseFloat(lhs.getDistance()) / Float.parseFloat(lhs.getPrice());
                float rhsZheKou = 1 - Float.parseFloat(rhs.getDistance()) / Float.parseFloat(rhs.getPrice());
                return Float.compare(rhsZheKou, lhsZheKou);
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sort() {
        super.sort();
    }
}
