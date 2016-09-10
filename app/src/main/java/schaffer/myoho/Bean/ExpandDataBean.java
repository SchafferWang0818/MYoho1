package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/9/10.
 */
public class ExpandDataBean {
    public String parentTitle;
    public List<ExpandDataChildBean> childList;

    public ExpandDataBean(String parentTitle, List<ExpandDataChildBean> childList) {
        this.parentTitle = parentTitle;
        this.childList = childList;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public List<ExpandDataChildBean> getChildList() {
        return childList;
    }

    public void setChildList(List<ExpandDataChildBean> childList) {
        this.childList = childList;
    }


}
