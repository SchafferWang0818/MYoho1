package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/9/3.
 */
public class CartGoodsBean {

    /**
     * scuess : ok
     * cart : [{"title":"nike1","imgpath":"menshose2.jpg","color":"黑色","size":"40码","price":"268","num":"1"},{"title":"nike2","imgpath":"g1.jpg","color":"红色","size":"XL","price":"248","num":"0"},{"title":"nike3","imgpath":"i2.jpg","color":"白色","size":"XXL","price":"468","num":"1"},{"title":"nike4","imgpath":"menshose5.jpg","color":"蓝色","size":"49码","price":"258","num":"3"},{"title":"nike5","imgpath":"menpants2.jpg","color":"绿色","size":"X","price":"168","num":"1"},{"title":"nike6","imgpath":"life.jpg","color":"白色","size":"F","price":"668","num":"6"},{"title":"nike7","imgpath":"f1.jpg","color":"黑色","size":"X","price":"368","num":"1"}]
     */

    private String scuess;
    /**
     * title : nike1
     * imgpath : menshose2.jpg
     * color : 黑色
     * size : 40码
     * price : 268
     * num : 1
     */

    private List<CartBean> cart;

    public String getScuess() {
        return scuess;
    }

    public void setScuess(String scuess) {
        this.scuess = scuess;
    }

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        private String title;
        private String imgpath;
        private String color;
        private String size;
        private String price;
        private String num;
        public int type;
        public boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
