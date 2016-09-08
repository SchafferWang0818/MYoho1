package schaffer.myoho.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

import schaffer.myoho.Base.MyApplication;
import schaffer.myoho.Bean.CartGoodsBean;
import schaffer.myoho.UserConfig.User;

/**
 * Created by a7352 on 2016/8/22.
 */
public class SPUtils {

    private static List<CartGoodsBean.CartBean> cart;

    public static void save(String key, String value) {
        MyApplication.app.getSharedPreferences("config", Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public static String get(String key) {
        return MyApplication.app.getSharedPreferences("config", Context.MODE_PRIVATE).getString(key, "");
    }

    public static boolean getUserConfigValid() {
        long time = System.currentTimeMillis();
        String userStr = MyApplication.app.getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", "");
        User user = new Gson().fromJson(userStr, User.class);
        MLog.w("user<--json:" + user);
        if (user != null) {
            long temp = user.getLastLoginTime() + user.getLoginValidTime();
            return time < temp;
        }
        return false;
    }

    public static void setUserConfig(User user) {
        String s = new Gson().toJson(user);
        MLog.w("user-->json:" + s);
        MyApplication.app.getSharedPreferences("user", Context.MODE_PRIVATE).edit().putString("user", s).commit();
    }

    public static User getUserConfig(){
        String json = MyApplication.app.getSharedPreferences("user", Context.MODE_PRIVATE).getString("user", "");
        User user = new Gson().fromJson(json, User.class);
        return user;
    }

    public static void clearCartData() {
        MyApplication.app.getSharedPreferences("cart_data", Context.MODE_PRIVATE).edit().clear().commit();
    }

    //需要得到本地的sp中的购物车中的量和内容
    public static int getLocalCartGoodsNum() {
        String con = MyApplication.app.getSharedPreferences("cart_data", Context.MODE_PRIVATE).getString("cart_data", "");
        CartGoodsBean cartGoodsBean = new Gson().fromJson(con, CartGoodsBean.class);
        if (cartGoodsBean == null) {
            return 0;
        }
        cart = cartGoodsBean.getCart();
        if (cart == null) {
            return 0;
        }
        return cart.size();
    }

    public static List<CartGoodsBean.CartBean> getLocalCartGoodsContent() {
        String con = MyApplication.app.getSharedPreferences("cart_data", Context.MODE_PRIVATE).getString("cart_data", "");
        CartGoodsBean cartGoodsBean = new Gson().fromJson(con, CartGoodsBean.class);
        if (cartGoodsBean == null) {
            return null;
        }
        return cartGoodsBean.getCart();
    }

    public static void saveLocalCartGoods(CartGoodsBean cartGoodsBean) {
        clearCartData();
        SharedPreferences.Editor cart_data = MyApplication.app.getSharedPreferences("cart_data", Context.MODE_PRIVATE).edit();
        String json = new Gson().toJson(cartGoodsBean);
        cart_data.putString("cart_data", json);
        cart_data.commit();
        MToast.notifys("更新数据成功");
    }

}
