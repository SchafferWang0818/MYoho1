package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/8/25.
 */
public class HotBrandBean {


    /**
     * sucessfully : ok
     * brand : [{"_id":"1","name":"nike","value":"nike","letter":"n","hotflag":"0","categoryId":null,"imgpath":"brand1.jpg"},{"_id":"2","name":"off the wall","value":"off","letter":"o","hotflag":"0","categoryId":null,"imgpath":"brand2.jpg"},{"_id":"3","name":"zuee","value":"zuee","letter":"z","hotflag":"1","categoryId":null,"imgpath":"brand3.jpg"},{"_id":"4","name":"boy","value":"boy","letter":"b","hotflag":"0","categoryId":null,"imgpath":"brand4.jpg"},{"_id":"5","name":"nn","value":"nn","letter":"n","hotflag":"1","categoryId":null,"imgpath":"brand5.jpg"},{"_id":"6","name":"big","value":"big","letter":"b","hotflag":"0","categoryId":null,"imgpath":"brand6.jpg"}]
     */

    private String sucessfully;
    /**
     * _id : 1
     * name : nike
     * value : nike
     * letter : n
     * hotflag : 0
     * categoryId : null
     * imgpath : brand1.jpg
     */

    private List<BrandBean> brand;

    public String getSucessfully() {
        return sucessfully;
    }

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public List<BrandBean> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandBean> brand) {
        this.brand = brand;
    }

    public static class BrandBean {
        private String _id;
        private String name;
        private String value;
        private String letter;
        private String hotflag;
        private Object categoryId;
        private String imgpath;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public String getHotflag() {
            return hotflag;
        }

        public void setHotflag(String hotflag) {
            this.hotflag = hotflag;
        }

        public Object getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Object categoryId) {
            this.categoryId = categoryId;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }
    }
}
