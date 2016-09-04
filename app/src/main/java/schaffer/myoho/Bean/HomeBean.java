package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by admin on 2016/8/29.
 */
public class HomeBean {

    /**
     * sucessfully : ok
     * brand : [{"imgpath":"brand1.jpg","shopId":"1","_id":"1","name":"nike","value":"nike","letter":"n","hotflag":"0","categoryId":null},{"imgpath":"brand2.jpg","shopId":"2","_id":"2","name":"off the wall","value":"off","letter":"o","hotflag":"0","categoryId":null},{"imgpath":"brand3.jpg","shopId":"3","_id":"3","name":"zuee","value":"zuee","letter":"z","hotflag":"1","categoryId":null},{"imgpath":"brand4.jpg","shopId":"4","_id":"4","name":"boy","value":"boy","letter":"b","hotflag":"0","categoryId":null},{"imgpath":"brand5.jpg","shopId":"5","_id":"5","name":"nn","value":"nn","letter":"n","hotflag":"1","categoryId":null},{"imgpath":"brand6.jpg","shopId":"6","_id":"6","name":"big","value":"big","letter":"b","hotflag":"0","categoryId":null},{"imgpath":"brand7.jpg","shopId":"7","_id":"7","name":"knomo","value":"nomo","letter":"k","hotflag":"1","categoryId":null},{"imgpath":"brand8.jpg","shopId":"8","_id":"8","name":"ovklab","value":"ovklab","letter":"o","hotflag":"0","categoryId":null}]
     * men : [{"imgpath":"men1.jpg","shopId":"38","_id":"38","name":"tee","value":"t","letter":"t","hotflag":"0","categoryId":null},{"imgpath":"men2.jpg","shopId":"9","_id":"9","name":"stussy","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"imgpath":"men3.jpg","shopId":"10","_id":"10","name":"izzue","value":"off","letter":"1","hotflag":"0","categoryId":null},{"imgpath":"men4.jpg","shopId":"11","_id":"11","name":"adidass","value":"adi","letter":"a","hotflag":"1","categoryId":null},{"imgpath":"men5.jpg","shopId":"12","_id":"12","name":"flyd","value":"flyd","letter":"f","hotflag":"0","categoryId":null},{"imgpath":"men6.jpg","shopId":"13","_id":"13","name":"blak jack","value":"blak","letter":"b","hotflag":"1","categoryId":null},{"imgpath":"menpants1.jpg","shopId":"14","_id":"14","name":"chinos","value":"stussy","letter":"c","hotflag":"0","categoryId":null},{"imgpath":"menpants2.jpg","shopId":"15","_id":"15","name":"hardlyevers","value":"off","letter":"h","hotflag":"0","categoryId":null},{"imgpath":"menpants3.jpg","shopId":"16","_id":"16","name":"preppy elite","value":"adi","letter":"p","hotflag":"1","categoryId":null},{"imgpath":"menpants4.jpg","shopId":"17","_id":"17","name":"carhartt wip","value":"flyd","letter":"c","hotflag":"0","categoryId":null},{"imgpath":"menpants5.jpg","shopId":"18","_id":"18","name":"5cm","value":"cao","letter":"5","hotflag":"1","categoryId":null},{"imgpath":"menpants6.jpg","shopId":"19","_id":"19","name":"鬼洗","value":"blak","letter":"g","hotflag":"1","categoryId":null},{"imgpath":"menshose1.jpg","shopId":"20","_id":"20","name":"sneaker","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"imgpath":"menshose2.jpg","shopId":"21","_id":"21","name":"converse","value":"off","letter":"c","hotflag":"0","categoryId":null},{"imgpath":"menshose3.jpg","shopId":"22","_id":"22","name":"onitsuka tiger","value":"adi","letter":"o","hotflag":"1","categoryId":null},{"imgpath":"menshose4.jpg","shopId":"23","_id":"23","name":"head","value":"flyd","letter":"h","hotflag":"0","categoryId":null},{"imgpath":"menshose5.jpg","shopId":"24","_id":"24","name":"gaws","value":"cao","letter":"g","hotflag":"1","categoryId":null},{"imgpath":"menshose6.jpg","shopId":"25","_id":"25","name":"new balance","value":"blak","letter":"n","hotflag":"1","categoryId":null}]
     * menpants : [{"imgpath":"menpants1.jpg","shopId":"14","_id":"14","name":"chinos","value":"stussy","letter":"c","hotflag":"0","categoryId":null},{"imgpath":"menpants2.jpg","shopId":"15","_id":"15","name":"hardlyevers","value":"off","letter":"h","hotflag":"0","categoryId":null},{"imgpath":"menpants3.jpg","shopId":"16","_id":"16","name":"preppy elite","value":"adi","letter":"p","hotflag":"1","categoryId":null},{"imgpath":"menpants4.jpg","shopId":"17","_id":"17","name":"carhartt wip","value":"flyd","letter":"c","hotflag":"0","categoryId":null},{"imgpath":"menpants5.jpg","shopId":"18","_id":"18","name":"5cm","value":"cao","letter":"5","hotflag":"1","categoryId":null},{"imgpath":"menpants6.jpg","shopId":"19","_id":"19","name":"鬼洗","value":"blak","letter":"g","hotflag":"1","categoryId":null}]
     * accessories : [{"imgpath":"accessories1.jpg","shopId":"26","_id":"26","name":"hat","value":"stussy","letter":"h","hotflag":"0","categoryId":null},{"imgpath":"accessories2.jpg","shopId":"27","_id":"27","name":"glasses","value":"off","letter":"g","hotflag":"0","categoryId":null},{"imgpath":"accessories3.jpg","shopId":"28","_id":"28","name":"digital","value":"adi","letter":"d","hotflag":"1","categoryId":null},{"imgpath":"accessories4.jpg","shopId":"29","_id":"29","name":"bags","value":"flyd","letter":"b","hotflag":"0","categoryId":null},{"imgpath":"accessories5.jpg","shopId":"30","_id":"30","name":"木九十","value":"cao","letter":"m","hotflag":"1","categoryId":null},{"imgpath":"accessories6.jpg","shopId":"31","_id":"31","name":"emie","value":"blak","letter":"e","hotflag":"1","categoryId":null}]
     * other : [{"imgpath":"3c.png","shopId":"32","_id":"32","name":"Skullcandy","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"imgpath":"home.jpg","shopId":"33","_id":"33","name":"home","value":"off","letter":"h","hotflag":"0","categoryId":null},{"imgpath":"life.jpg","shopId":"34","_id":"34","name":"life","value":"adi","letter":"l","hotflag":"1","categoryId":null},{"imgpath":"mei.jpg","shopId":"35","_id":"35","name":"mei","value":"flyd","letter":"m","hotflag":"0","categoryId":null},{"imgpath":"shou.jpg","shopId":"36","_id":"36","name":"cassio","value":"cao","letter":"c","hotflag":"1","categoryId":null},{"imgpath":"stationery.jpg","shopId":"37","_id":"37","name":"stationery","value":"blak","letter":"s","hotflag":"1","categoryId":null}]
     */

    private String sucessfully;
    private List<BrandBean> brand;
    private List<BrandBean> men;
    private List<BrandBean> menpants;
    private List<BrandBean> accessories;
    private List<BrandBean> other;

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public void setMen(List<BrandBean> men) {
        this.men = men;
    }

    public void setMenpants(List<BrandBean> menpants) {
        this.menpants = menpants;
    }

    public void setAccessories(List<BrandBean> accessories) {
        this.accessories = accessories;
    }

    public void setOther(List<BrandBean> other) {
        this.other = other;
    }

    public String getSucessfully() {
        return sucessfully;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public List<BrandBean> getMen() {
        return men;
    }

    public List<BrandBean> getMenpants() {
        return menpants;
    }

    public List<BrandBean> getAccessories() {
        return accessories;
    }

    public List<BrandBean> getOther() {
        return other;
    }

    public static class BrandBean {
        /**
         * imgpath : brand1.jpg
         * shopId : 1
         * _id : 1
         * name : nike
         * value : nike
         * letter : n
         * hotflag : 0
         * categoryId : null
         */

        private String imgpath;
        private String shopId;
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public String getShopId() {
            return shopId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getLetter() {
            return letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }
    }

    public static class MenBean {
        /**
         * imgpath : men1.jpg
         * shopId : 38
         * _id : 38
         * name : tee
         * value : t
         * letter : t
         * hotflag : 0
         * categoryId : null
         */

        private String imgpath;
        private String shopId;
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public String getShopId() {
            return shopId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getLetter() {
            return letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }
    }

    public static class MenpantsBean {
        /**
         * imgpath : menpants1.jpg
         * shopId : 14
         * _id : 14
         * name : chinos
         * value : stussy
         * letter : c
         * hotflag : 0
         * categoryId : null
         */

        private String imgpath;
        private String shopId;
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public String getShopId() {
            return shopId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getLetter() {
            return letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }
    }

    public static class AccessoriesBean {
        /**
         * imgpath : accessories1.jpg
         * shopId : 26
         * _id : 26
         * name : hat
         * value : stussy
         * letter : h
         * hotflag : 0
         * categoryId : null
         */

        private String imgpath;
        private String shopId;
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public String getShopId() {
            return shopId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getLetter() {
            return letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }
    }

    public static class OtherBean {
        /**
         * imgpath : 3c.png
         * shopId : 32
         * _id : 32
         * name : Skullcandy
         * value : stussy
         * letter : s
         * hotflag : 0
         * categoryId : null
         */

        private String imgpath;
        private String shopId;
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public String getShopId() {
            return shopId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getLetter() {
            return letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }
    }
}
