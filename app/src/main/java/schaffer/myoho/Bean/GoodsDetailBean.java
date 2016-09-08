package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/9/1.
 */
public class GoodsDetailBean {

    private String sucessfully;
    /**
     * _id : 1
     * title : 奔跑吧兄弟 撕名牌魔术贴徽章T恤
     * price : 139.00
     * ShopId : 1
     * GoodsUrl : http://item.yohobuy.com/product/pro_204497_273231/
     * time : 1439984566
     * discount : 139.00
     * SexId : 0
     * CategoryId : 0
     * ColorId : 0
     * advertId : 0
     */

    private List<GoodsBean> goods;
    /**
     * _id : 44
     * imgpath : a1.jpg
     * goodsId : 1
     * UserId : null
     * NewsId : null
     * advertId : null
     * shopId : 1
     */

    private List<ImgBean> img;
    /**
     * _id : 1
     * imgpath : v1.jpg
     * goodsId : 1
     */

    private List<ImgvaleBean> imgvale;

    public String getSucessfully() {
        return sucessfully;
    }

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public List<ImgvaleBean> getImgvale() {
        return imgvale;
    }

    public void setImgvale(List<ImgvaleBean> imgvale) {
        this.imgvale = imgvale;
    }

    public static class GoodsBean {
        private String _id;
        private String title;
        private String price;
        private String ShopId;
        private String GoodsUrl;
        private String time;
        private String discount;
        private String SexId;
        private String CategoryId;
        private String ColorId;
        private String advertId;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getGoodsUrl() {
            return GoodsUrl;
        }

        public void setGoodsUrl(String GoodsUrl) {
            this.GoodsUrl = GoodsUrl;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getSexId() {
            return SexId;
        }

        public void setSexId(String SexId) {
            this.SexId = SexId;
        }

        public String getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(String CategoryId) {
            this.CategoryId = CategoryId;
        }

        public String getColorId() {
            return ColorId;
        }

        public void setColorId(String ColorId) {
            this.ColorId = ColorId;
        }

        public String getAdvertId() {
            return advertId;
        }

        public void setAdvertId(String advertId) {
            this.advertId = advertId;
        }
    }

    public static class ImgBean {
        private String _id;
        private String imgpath;
        private String goodsId;
        private Object UserId;
        private Object NewsId;
        private Object advertId;
        private String shopId;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public Object getUserId() {
            return UserId;
        }

        public void setUserId(Object UserId) {
            this.UserId = UserId;
        }

        public Object getNewsId() {
            return NewsId;
        }

        public void setNewsId(Object NewsId) {
            this.NewsId = NewsId;
        }

        public Object getAdvertId() {
            return advertId;
        }

        public void setAdvertId(Object advertId) {
            this.advertId = advertId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
    }

    public static class ImgvaleBean {
        private String _id;
        private String imgpath;
        private String goodsId;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }
}
