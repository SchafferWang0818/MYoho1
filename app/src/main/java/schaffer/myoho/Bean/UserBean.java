package schaffer.myoho.Bean;

/**
 * Created by a7352 on 2016/9/3.
 */
public class UserBean {
    public int useId;
    public String imgPath;
    public String userName;
    public String token;

    public UserBean() {

    }

    public UserBean(int userNum, String imgPath, String userName, String token) {
        this.useId = userNum;
        this.imgPath = imgPath;
        this.userName = userName;
        this.token = token;
    }

    public UserBean(int userNum, String imgPath, String userName) {
        this.useId = userNum;
        this.imgPath = imgPath;
        this.userName = userName;
    }

}
