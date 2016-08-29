package schaffer.myoho.Bean;

/**
 * Created by a7352 on 2016/8/25.
 */
public class PagerViewBean {


    /**
     * _id : 1
     * imgpath : top1.jpg
     * goodsId : null
     * UserId : null
     * NewsId : null
     * advertId : 1
     * shopId : null
     */

    private String _id;
    private String imgpath;
    private Object goodsId;
    private Object UserId;
    private Object NewsId;
    private String advertId;
    private Object shopId;

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

    public Object getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Object goodsId) {
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

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public Object getShopId() {
        return shopId;
    }

    public void setShopId(Object shopId) {
        this.shopId = shopId;
    }
}
