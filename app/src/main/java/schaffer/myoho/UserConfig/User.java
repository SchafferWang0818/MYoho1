package schaffer.myoho.UserConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import schaffer.myoho.Bean.CartGoodsBean;

/**
 * Created by a7352 on 2016/9/3.
 */
public class User implements Serializable {
    /**
     * useId : 1
     * imgPath : 1
     * userName : userName
     * token : token
     * lastLoginTime : 1
     * loginValidTime : 1
     */

    private int useId;
    private String imgPath;
    private String userName;
    private String token;
    private long lastLoginTime;
    private long loginValidTime;
    public List<CartGoodsBean.CartBean> careList;

    public int getUseId() {
        return useId;
    }

    public void setUseId(int useId) {
        this.useId = useId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(int lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getLoginValidTime() {
        return loginValidTime;
    }

    public void setLoginValidTime(int loginValidTime) {
        this.loginValidTime = loginValidTime;
    }

    public User(int useId, String imgPath, String userName, String token, long lastLoginTime, long loginValidTime) {
        this();
        this.useId = useId;
        this.imgPath = imgPath;
        this.userName = userName;
        this.token = token;
        this.lastLoginTime = lastLoginTime;
        this.loginValidTime = loginValidTime;
    }

    public User(int loginValidTime, int lastLoginTime, String userName, String imgPath, int useId) {
        this();
        this.loginValidTime = loginValidTime;
        this.lastLoginTime = lastLoginTime;
        this.userName = userName;
        this.imgPath = imgPath;
        this.useId = useId;
    }

    public User() {
        careList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "\"useId\":" + useId + ", \"imgPath\":\"" + imgPath + '\"' + ",\"userName\":\"" + userName + '\"' +
                ",\"token\":\"" + token + '\"' + ",\"lastLoginTime\":" + lastLoginTime +
                ", \"loginValidTime\":" + loginValidTime + '}';
    }
}
