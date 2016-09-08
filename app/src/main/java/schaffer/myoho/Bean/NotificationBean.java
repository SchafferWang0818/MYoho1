package schaffer.myoho.Bean;

/**
 * Created by a7352 on 2016/9/8.
 */
public class NotificationBean {
    /**
     * title1 : asdsadsad
     * title2 : dsdasdsad
     * content : asdsadsadas
     * imgPath : adsadsadsadsa
     * turnPath : asdsadsadasd
     */

    /*{"title1" : "title1","title2":"title2","content":"content","imgPath":"http://pic1.win4000.com/pic/b/8a/dd9a950208.jpg","turnPath":"http://www.baidu.com"}*/

    private String title1;
    private String title2;
    private String content;
    private String imgPath;
    private String turnPath;

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTurnPath() {
        return turnPath;
    }

    public void setTurnPath(String turnPath) {
        this.turnPath = turnPath;
    }
}
