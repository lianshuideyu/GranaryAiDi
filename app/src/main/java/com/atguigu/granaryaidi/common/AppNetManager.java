package com.atguigu.granaryaidi.common;

/**
 * Created by Administrator on 2017/7/18.
 */

public class AppNetManager {


    public static final String LIVE_URL = "http://live.bilibili.com/AppNewIndex/common?" +
            "_device=android&appkey=1d8b6e7d45233436&build=501000&mobi" +
            "_app=android&platform=android&scale=hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b";

    public static final String RECOMMEND_URL = "http://app.bilibili.com/x/feed/index?" +
            "appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&" +
            "pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4";

    public static final String FOLLOW_URL = "http://bangumi.bilibili.com/api/app_index_page_v4?" +
            "build=3940&device=phone&mobi_app=iphone&platform=ios";

    public static final String ALL_TYPE_URL = "http://app.bilibili.com/x/v2/region?appkey=1d8b6e7d45233436&build=501000" +
            "&mobi_app=android&platform=android&ts=1490257145000&ver=2939539575990722411&sign=8bbab589312b98dbfff698095d5e0a96";

    public static final String TYPE_URL = "http://app.bilibili.com/x/v2/show/region?appkey=1d8b6e7d45233436&build=501000&" +
            "mobi_app=android&platform=android&ts=1490014674000&sign=93edb7634f38498a60e5c3ad0b8b0974";

    public static final String FIND_URL = "http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436&build=501000&limit=50&" +
            "mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf94fa9a0d6876cb85756c37c4adc";

    public static final String SHOP_MALL = "http://bmall.bilibili.com/api/product/list.do?pn=1&ps=6";

    //******************************************//
    //提供web应用的地址
    public static final String BASE_URL = "http://app.bilibili.com/x/feed/";
}
