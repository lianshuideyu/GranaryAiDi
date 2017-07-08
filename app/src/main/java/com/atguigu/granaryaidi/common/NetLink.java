package com.atguigu.granaryaidi.common;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NetLink {


    /**
     * 打开webview使用的字段
     */
    public static final String HTML_URL = "htmlurl";

    public static final String HTML_TITLE = "htmlname";
    /**
     * 商品--分类--二级页面使用
     */
    public static final String SHOP_URL = "shopurl";

    /**
     * 商品--品牌--打开二级页面使用
     */
    public static final String SHOP_PINPAI_LIST = "shoppinpai";

    public static final String DAREN_DETAILS = "darendetails";

//------------------------------------------------------------------------------------
    public static final int PAGE = 1;

    //商品模块--分类首页
    public static final String SHOP_CLASSIFY = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //商品模块--品牌首页
    public static final String SHOP_PINPAI = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    //商品模块--专题首页
    public static final String SHOP_SPECIAL = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";

    //商品模块--达人首页
    public static final String DAREN_DEFAULT = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";

    //商品模块--首页--首页
    public static final String SHOP_HOME = "http://mobile.iliangcang.com/goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";



    /**
     * 商店模块----分类页面--的二级页面链接
     */
    //商店 - 分类 - 家居
    public static final String TYPE_HOME_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=";
    public static final String TYPE_HOME_END_URL = "&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    public static final String HOUSE_URL = TYPE_HOME_FROEPART_URL + PAGE + TYPE_HOME_END_URL;
    //商店 - 分类 - 家具
    public static final String TYPE_FITMENT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0055&count=10&coverId=1&page=";
    public static final String TYPE_FITMENT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String FITMENT_URL = TYPE_FITMENT_FROEPART_URL + PAGE + TYPE_FITMENT_END_URL;
    //商店 - 分类 - 文具
    public static final String TYPE_STATIONERY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0062&count=10&coverId=1&page=";
    public static final String TYPE_STATIONERY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String STATIONERY_URL = TYPE_STATIONERY_FROEPART_URL + PAGE + TYPE_STATIONERY_END_URL;
    //商店 - 分类 - 数码
    public static final String TYPE_NUMERICAL_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0069&count=10&coverId=1&page=";
    public static final String TYPE_NUMERICAL_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String NUMERICAL_URL = TYPE_NUMERICAL_FROEPART_URL + PAGE + TYPE_NUMERICAL_END_URL;

    //商店 - 分类 - 玩乐
    public static final String TYPE_PLAY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0077&count=10&coverId=1&page=";
    public static final String TYPE_PLAY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String PLAY_URL = TYPE_PLAY_FROEPART_URL + PAGE + TYPE_PLAY_END_URL;

    //商店 - 分类 - 厨卫
    public static final String TYPE_KITCHEN_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0082&count=10&coverId=1&page=";
    public static final String TYPE_KITCHEN_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String KITCHEN_URL = TYPE_KITCHEN_FROEPART_URL + PAGE + TYPE_KITCHEN_END_URL;

    //商店 - 分类 - 美食
    public static final String TYPE_CATE_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0092&count=10&coverId=1&page=";
    public static final String TYPE_CATE_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String CATE_URL = TYPE_CATE_FROEPART_URL + PAGE + TYPE_CATE_END_URL;

    //商店 - 分类 - 男装
    public static final String TYPE_MENWEAR_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0101&count=10&coverId=1&page=";
    public static final String TYPE_MENWEAR_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String MENWEAR_URL = TYPE_MENWEAR_FROEPART_URL + PAGE + TYPE_MENWEAR_END_URL;
    //商店 - 分类 - 女装
    public static final String TYPE_FROCK_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0112&count=10&coverId=1&page=";
    public static final String TYPE_FROCK_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String FROCK_URL = TYPE_FROCK_FROEPART_URL + PAGE + TYPE_FROCK_END_URL;

    //商店 - 分类 - 童装
    public static final String TYPE_BABYWEAR_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0125&count=10&coverId=1&page=";
    public static final String TYPE_BABYWEAR_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BABYWEAR_URL = TYPE_BABYWEAR_FROEPART_URL + PAGE + TYPE_BABYWEAR_END_URL;

    //商店 - 分类 - 鞋包
    public static final String TYPE_SHOE_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0129&count=10&coverId=1&page=";
    public static final String TYPE_SHOE_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String SHOE_URL = TYPE_SHOE_FROEPART_URL + PAGE + TYPE_SHOE_END_URL;

    //商店 - 分类 - 配饰
    public static final String TYPE_ACC_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0141&count=10&coverId=1&page=";
    public static final String TYPE_ACC_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String ACC_URL = TYPE_ACC_FROEPART_URL + PAGE + TYPE_ACC_END_URL;

    //商店 - 分类 - 美护
    public static final String TYPE_BEAUTY_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0154&count=10&coverId=1&page=";
    public static final String TYPE_BEAUTY_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BEAUTY_URL = TYPE_BEAUTY_FROEPART_URL + PAGE + TYPE_BEAUTY_END_URL;

    //商店 - 分类 - 户外
    public static final String TYPE_OUTDOORS_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0166&count=10&coverId=1&page=";
    public static final String TYPE_OUTDOORS_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String OUTDOORS_URL = TYPE_OUTDOORS_FROEPART_URL + PAGE + TYPE_OUTDOORS_END_URL;
    //商店 - 分类 - 植物
    public static final String TYPE_PLANT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0172&count=10&coverId=1&page=";
    public static final String TYPE_PLANT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String PLANT_URL = TYPE_PLANT_FROEPART_URL + PAGE + TYPE_PLANT_END_URL;

    //商店 - 分类 - 图书
    public static final String TYPE_BOOK_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0182&count=10&coverId=1&page=";
    public static final String TYPE_BOOK_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String BOOK_URL = TYPE_BOOK_FROEPART_URL + PAGE + TYPE_BOOK_END_URL;
    //商店 - 分类 - 礼物
    public static final String TYPE_GIFT_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0190&count=10&coverId=1&page=";
    public static final String TYPE_GIFT_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String GIFT_URL = TYPE_GIFT_FROEPART_URL + PAGE + TYPE_GIFT_END_URL;
    //商店 - 分类 - 推荐
    public static final String TYPE_RECOMMEND_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0198&count=10&coverId=1&page=";
    public static final String TYPE_RECOMMEND_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String RECOMMEND_URL = TYPE_RECOMMEND_FROEPART_URL + PAGE + TYPE_RECOMMEND_END_URL;
    //商店 - 分类 - 艺术
    public static final String TYPE_ART_FROEPART_URL = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0214&count=10&coverId=1&page=";
    public static final String TYPE_ART_END_URL = "&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    public static final String ART_URL = TYPE_ART_FROEPART_URL + PAGE + TYPE_ART_END_URL;


    /**
     * 商店模块----礼物页面的二级页面链接
     */

    //商店 - 礼物 - 顶部图片
    public static final String SHOP_GIFT_TOP = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=7&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //商店 - 礼物 - 节日
    public static final String SHOP_GIFT_FESTIVAL = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=1&page=1&sig=DFD7151CC9D607E396FE108FE270FFF3%7C366534120395468&v=1.0";

    //商店 - 礼物 - 爱情
    public static final String SHOP_GIFT_LOVE = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=2&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //商店 - 礼物 - 生日
    public static final String SHOP_GIFT_BRITHDAY = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=3&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //商店 - 礼物 - 朋友
    public static final String SHOP_GIFT_FRIENDS = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=4&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //商店 - 礼物 - 孩子
    public static final String SHOP_GIFT_CHILD = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=5&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";

    //商店 - 礼物 - 父母
    public static final String SHOP_GIFT_PARENT = "http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=6&page=1&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0";


    /**
     * 商店模块----品牌页面的二级页面链接
     */
    //品牌二级页面中 品牌产品的 链接 前半部分---中间需要拼接的是从品牌一级页面传来的brand_id=840字段
    public static final String SHOP_PINPAI_LISTSTART = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=";
    //品牌二级页面中 品牌产品的 链接 后半部分
    public static final String SHOP_PINPAI_LISTEND = "&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";

    /*
    * http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id=840&
    * count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0*/


    /**
     * 达人的二级详情页面 -- 推荐页面
     */
    //中间加上owner_id的值，owner_id的值与 达人一级界面的uid字段 值相同
    public static final String DAREN_DETAILS_RECOMMEND_START = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id=";
    public static final String DAREN_DETAILS_RECOMMEND_END = "&page=1&sig=5715DFAE35D85EA29846D090DBBF8753%7C557744010558468&v=1.0";

    //http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10
    // &owner_id=999999673&page=1&sig=5715DFAE35D85EA29846D090DBBF8753%7C557744010558468&v=1.0
    //关注页面
    public static final String DAREN_DETAILS_GUANZHU_START = "http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=12&owner_id=";
    public static final String DAREN_DETAILS_GUANZHU_END = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    /*http://mobile.iliangcang.com/user/masterFollow?app_key=Android&count=12
    &owner_id=85&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0*/

    /*
    * http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12
    * &owner_id=8051&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0*/
    //粉丝
    public static final String DAREN_DETAILS_FANS_START = "http://mobile.iliangcang.com/user/masterFollowed?app_key=Android&count=12&owner_id=";
    public static final String DAREN_DETAILS_FANS_END = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";


    /*http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=10&owner_id=
    455123940&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0*/
    //粉丝
    public static final String DAREN_DETAILS_LIKE_START = "http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=10&owner_id=";
    public static final String DAREN_DETAILS_LIKE_END = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";


    /*http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=
    258055&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0*/
    //商品 详情 页面 链接----不同商品不同的bean
    public static final String GOODS_DETAILS_START = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=";
    public static final String GOODS_DETAILS_END = "&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";


}
