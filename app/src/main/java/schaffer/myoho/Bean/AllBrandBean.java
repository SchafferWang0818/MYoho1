package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/8/25.
 */
public class AllBrandBean {
    /**
     * sucessfully : ok
     * brand : [{"_id":"1","name":"nike","value":"nike","letter":"n","hotflag":"0","categoryId":null},{"_id":"2","name":"off the wall","value":"off","letter":"o","hotflag":"0","categoryId":null},{"_id":"3","name":"zuee","value":"zuee","letter":"z","hotflag":"1","categoryId":null},{"_id":"4","name":"boy","value":"boy","letter":"b","hotflag":"0","categoryId":null},{"_id":"5","name":"nn","value":"nn","letter":"n","hotflag":"1","categoryId":null},{"_id":"6","name":"big","value":"big","letter":"b","hotflag":"0","categoryId":null},{"_id":"7","name":"knomo","value":"nomo","letter":"k","hotflag":"1","categoryId":null},{"_id":"8","name":"ovklab","value":"ovklab","letter":"o","hotflag":"0","categoryId":null},{"_id":"9","name":"stussy","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"_id":"10","name":"izzue","value":"off","letter":"1","hotflag":"0","categoryId":null},{"_id":"11","name":"adidass","value":"adi","letter":"a","hotflag":"1","categoryId":null},{"_id":"12","name":"flyd","value":"flyd","letter":"f","hotflag":"0","categoryId":null},{"_id":"13","name":"blak jack","value":"blak","letter":"b","hotflag":"1","categoryId":null},{"_id":"14","name":"chinos","value":"stussy","letter":"c","hotflag":"0","categoryId":null},{"_id":"15","name":"hardlyevers","value":"off","letter":"h","hotflag":"0","categoryId":null},{"_id":"16","name":"preppy elite","value":"adi","letter":"p","hotflag":"1","categoryId":null},{"_id":"17","name":"carhartt wip","value":"flyd","letter":"c","hotflag":"0","categoryId":null},{"_id":"18","name":"5cm","value":"cao","letter":"5","hotflag":"1","categoryId":null},{"_id":"19","name":"鬼洗","value":"blak","letter":"g","hotflag":"1","categoryId":null},{"_id":"20","name":"sneaker","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"_id":"21","name":"converse","value":"off","letter":"c","hotflag":"0","categoryId":null},{"_id":"22","name":"onitsuka tiger","value":"adi","letter":"o","hotflag":"1","categoryId":null},{"_id":"23","name":"head","value":"flyd","letter":"h","hotflag":"0","categoryId":null},{"_id":"24","name":"gaws","value":"cao","letter":"g","hotflag":"1","categoryId":null},{"_id":"25","name":"new balance","value":"blak","letter":"n","hotflag":"1","categoryId":null},{"_id":"26","name":"hat","value":"stussy","letter":"h","hotflag":"0","categoryId":null},{"_id":"27","name":"glasses","value":"off","letter":"g","hotflag":"0","categoryId":null},{"_id":"28","name":"digital","value":"adi","letter":"d","hotflag":"1","categoryId":null},{"_id":"29","name":"bags","value":"flyd","letter":"b","hotflag":"0","categoryId":null},{"_id":"30","name":"木九十","value":"cao","letter":"m","hotflag":"1","categoryId":null},{"_id":"31","name":"emie","value":"blak","letter":"e","hotflag":"1","categoryId":null},{"_id":"32","name":"Skullcandy","value":"stussy","letter":"s","hotflag":"0","categoryId":null},{"_id":"33","name":"home","value":"off","letter":"h","hotflag":"0","categoryId":null},{"_id":"34","name":"life","value":"adi","letter":"l","hotflag":"1","categoryId":null},{"_id":"35","name":"mei","value":"flyd","letter":"m","hotflag":"0","categoryId":null},{"_id":"36","name":"cassio","value":"cao","letter":"c","hotflag":"1","categoryId":null},{"_id":"37","name":"stationery","value":"blak","letter":"s","hotflag":"1","categoryId":null},{"_id":"38","name":"tee","value":"t","letter":"t","hotflag":"0","categoryId":null}]
     */

    private String sucessfully;
    private List<BrandBean> brand;

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public String getSucessfully() {
        return sucessfully;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public static class BrandBean {
        /**
         * _id : 1
         * name : nike
         * value : nike
         * letter : n
         * hotflag : 0
         * categoryId : null
         */

        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;

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
