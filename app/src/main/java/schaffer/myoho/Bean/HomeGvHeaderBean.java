package schaffer.myoho.Bean;

/**
 * Created by a7352 on 2016/8/29.
 */
public class HomeGvHeaderBean {
    public int imgRes;
    public String title;

    public HomeGvHeaderBean(int imgRes, String title) {
        this.imgRes = imgRes;
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
