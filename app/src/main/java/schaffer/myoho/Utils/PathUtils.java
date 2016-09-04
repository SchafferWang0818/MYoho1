package schaffer.myoho.Utils;


public class PathUtils {

    //      请求地址：http://www.iwens.org/School_Sky/
    public static final String IMG_HEAD = "http://www.iwens.org/School_Sky/Img/";
    //        图片地址: http://www.iwens.org/School_Sky/Img/
    public static final String JSON_HEAD = "http://www.iwens.org/School_Sky/";
    //        请求方式:POST
//        获取首页广告接口: yohoadvert.php
    public static final String JSON_PAGE = JSON_HEAD + "yohoadvert.php";

    public static final String JSON_HOME_PAGER = JSON_HEAD + "homepager.php";
    public static final String JSON_HOME_PAGE_BODY = "parames={\\\"shop\\\":\\\"1\\\"}";
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

    public static final String JSON_GOODS_DETAIL = JSON_HEAD + "goodsvalue.php";
    public static final String JSON_GOODS_DETAIL_BODY = "parames={\"goods_id\":\"1\"}";
    //        获取商品详情: goodsvalue.php
    //        参数: parames={\"goods_id\":\""+id+"\"}


    //        收藏商品接口: CollectionShop.php
    //        参数:
    //        添加购物车接口: addcart.php
    //        参数: parames={\"goodsId\":\""+list.get(0)._id+"\"," +
    //        "\"userId\":\""+((MyApplication)getApplicationContext()).userId+"\"," +
    //        "\"colorId\":\""+colorID+"\"," +
    //        "\"sizeId\":\""+sizeID+"\"}
    public static final String JSON_CART_LIST_HEAD = JSON_HEAD + "cartlist.php";
    public static int JSON_CART_LIST_USER_ID;
    public static final String JSON_CART_LIST_BODY = "parames={\"userId\":"+JSON_CART_LIST_USER_ID+"}";

    //        购物车列表接口: cartlist.php
    //        参数: parames={"userId":"+((MyApplication)getApplicationContext()).userId+"\"}
    //        购物车提交商品接口: UpOrder.php
    //        参数: parames={userid:1,goods[{goodsid:1,color:hong,size:x
    //        num:1},{goodsid:1,color:hong,size:x
    //        num:1}]}
//    public static final String JSON_GOODS = JSON_HEAD + "";

    public static final String ALL_BRAND_BODY = "parames={\\\"page\\\":\\\"10\\\"}";

    //        热门品牌接口: hotbrand.php
    public static final String JSON_HORIZONTAL_RV = JSON_HEAD + "hotbrand.php";

    //        获取关注列表接口: follow.php
    public static final String JSON_FOLLOW = JSON_HEAD + "follow.php";
    public static final String JSON_GOODS = JSON_HEAD + "";
    //        参数: parames={\"userid\":\"
    //        userId+"\"}
    public static final String JSON_GOODS_ORDER_CONFIRM = JSON_HEAD + "Confirm.php";
    //        确认订单接口: Confirm.php
    //        参数:
    public static final String JSON_LIST_GOODS_ORDER = JSON_HEAD + "OkOrder.php";
    //        订单列表接口: OkOrder.php
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

    public static final String JSON_CART_FLUSH = JSON_HEAD + "RefrashCart.php";
    public static final String JSON_NEWS = JSON_HEAD + "news.php";
    public static final String JSON_LOGIN = JSON_HEAD + "yohologin.php";
    public static final String JSON_REGIST = JSON_HEAD + "yohoregiste.php";
    public static final String JSON_HEAD_IMG_UPLOAD = JSON_HEAD + "yohouphead.php";
    public static final String JSON_BRAND_VALUE = JSON_HEAD + "brandvalue.php";
    public static final String JSON_VERSION = JSON_HEAD + "upVersion.php";
    //        获取服务器apk版本接口: upVersion.php
    //        参数:
}
