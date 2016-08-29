package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/8/25.
 */
public class AllBrandLetterBean {
    public String letterTitle;
    public List<AllBrandBean.BrandBean> list;

    public AllBrandLetterBean(String letterTitle, List<AllBrandBean.BrandBean> list) {
        this.letterTitle = letterTitle;
        this.list = list;

    }

    @Override
    public String toString() {
        return "AllBrandLetterBean{" +
                "letterTitle='" + letterTitle + '\'' +
                ", list=" + list +
                '}';
    }
}
