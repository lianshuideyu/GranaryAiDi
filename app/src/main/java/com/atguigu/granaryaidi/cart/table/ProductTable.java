package com.atguigu.granaryaidi.cart.table;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ProductTable {

    /*
   * 注意 ：
   *       1 把sql语句一定要确定没有问题
   *       2 数据库创建有问题的时候，如果需要重新创建 一定要卸载原来的应用
   *private String type ;//商品的型号
    private String cover_price;
    private String figure;//图标地址
    private String name;//商品名
    private String product_id;//识别码
    private int number = 1;
   * */
    public static final String TABLE_NAME = "products";

    public static final String COL_PRODUCTNAME = "productname";
    public static final String COL_PRODUCT_ID = "productid";
    public static final String COL_FIGURE = "figure";
    public static final String COL_TYPE = "type";
    public static final String COL_PRICE = "price";
    public static final String COL_NUMBER = "number";

    /**
     * 建表
     */
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + COL_PRODUCT_ID + " text primary key,"
            + COL_PRODUCTNAME + " text,"
            + COL_FIGURE + " text,"
            + COL_TYPE + " text,"
            + COL_PRICE + " text,"
            + COL_NUMBER + " integer)"
            ;
}
