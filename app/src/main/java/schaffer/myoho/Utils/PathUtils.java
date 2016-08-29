package schaffer.myoho.Utils;


public class PathUtils {

    //      请求地址：http://www.iwens.org/School_Sky/
    public static final String IMG_HEAD = "http://www.iwens.org/School_Sky/Img/";
    //        图片地址: http://www.iwens.org/School_Sky/Img/
    public static final String JSON_HEAD = "http://www.iwens.org/School_Sky/";
    //        请求方式:POST
//        获取首页广告接口: yohoadvert.php
    public static final String JSON_PAGE = JSON_HEAD + "yohoadvert.php";

    //        获取首页内容接口: homepager.php
    //        参数: parames={\"shop\":\"1\"}
//        首页推荐商品接口: recommend.php
//        参数: parames={\"page\":\"1\"}
//        分类页面boy接口: categoryboy.php
    public static final String JSON_CATE_BOY = JSON_HEAD + "categoryboy.php";

    public static final String JSON_CATE_GIRL = JSON_HEAD + "categorygirl.php";
    //        分类页面girl接口: categorygirl.php
    public static final String JSON_CATE_LIFESTYLE = JSON_HEAD + "categorylife.php";
    //        分类页面lifestyle接口: categorylife.php
//        分类页面toplistview接口: categoryvalue.php
//        参数:parames={\"id\":\""+parentId+"\"}
//        所有品牌接口: allbrand.php
//        参数: parames={\"page\":\"10\"}
    public static final String JSON_CATE_ALL_BRAND_HEAD = JSON_HEAD + "allbrand.php";
    public static final String ALL_BRAND_BODY = "parames={\\\"page\\\":\\\"10\\\"}";

    //        热门品牌接口: hotbrand.php
    public static final String JSON_HORIZONTAL_RV = JSON_HEAD + "hotbrand.php";

    //        参数:
//        获取关注列表接口: follow.php
    public static final String JSON_FOLLOW = JSON_HEAD + "follow.php";

//        参数:
//        获取商品详情: goodsvalue.php
//        参数: parames={\"goods_id\":\""+id+"\"}
//        收藏商品接口: CollectionShop.php
//        参数:
//        添加购物车接口: addcart.php
//        参数: parames={\"goodsId\":\""+list.get(0)._id+"\"," +
//        "\"userId\":\""+((MyApplication)getApplicationContext()).userId+"\"," +
//        "\"colorId\":\""+colorID+"\"," +
//        "\"sizeId\":\""+sizeID+"\"}
//        购物车列表接口: cartlist.php
//        参数: parames={\"userId\":\""+((MyApplication)getApplicationContext()).userId+"\"}
//        购物车提交商品接口: UpOrder.php
//        参数: parames={userid:1,goods[{goodsid:1,color:hong,size:x
//        num:1},{goodsid:1,color:hong,size:x
//        num:1}]}
//        订单列表接口: OkOrder.php
//        参数:
//        确认订单接口: Confirm.php
//        参数: parames={\"userid\":\"
//        userId+"\"}
//        刷新购物车列表接口: RefrashCart.php
//        参数:
//        获取新闻接口: news.php
//        参数:
//        登陆接口: yohologin.php
//        参数:
//        注册接口: yohoregiste.php
//        参数:
//        上传头像接口: yohouphead.php
//        参数:
//        获取品牌商品数据源: brandvalue.php
//        参数:
//        获取服务器apk版本接口: upVersion.php
//        参数:
}
