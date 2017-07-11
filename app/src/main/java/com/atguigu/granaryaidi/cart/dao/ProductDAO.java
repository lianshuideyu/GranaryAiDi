package com.atguigu.granaryaidi.cart.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.atguigu.granaryaidi.cart.GoodsBean;
import com.atguigu.granaryaidi.cart.db.HelperDB;
import com.atguigu.granaryaidi.cart.table.ProductTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ProductDAO {

    private final HelperDB helperDB;

    public ProductDAO(HelperDB helperDB) {

        this.helperDB = helperDB;
    }

    /*
    * private String type ;//商品的型号
    private String cover_price;
    private String figure;//图标地址
    private String name;//商品名
    private String product_id;//识别码
    private int number = 1;*/
    /**
     * 查询所有联系人产品
     *
     */
    public List<GoodsBean> getProducts() {

        //关联数据库
        SQLiteDatabase database = helperDB.getWritableDatabase();

        String sql = "select * from " + ProductTable.TABLE_NAME ;
        Cursor cursor = database.rawQuery(sql, null);

        List<GoodsBean> userInfos = new ArrayList<>();
        while (cursor.moveToNext()) {
            GoodsBean userInfo = new GoodsBean();

            userInfo.setName(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRODUCTNAME)));
            userInfo.setFigure(cursor.getString(cursor.getColumnIndex(ProductTable.COL_FIGURE)));
            userInfo.setNumber(cursor.getInt(cursor.getColumnIndex(ProductTable.COL_NUMBER)));
            userInfo.setCover_price(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRICE)));
            userInfo.setType(cursor.getString(cursor.getColumnIndex(ProductTable.COL_TYPE)));
            userInfo.setProduct_id(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRODUCT_ID)));

            userInfos.add(userInfo);
        }
        cursor.close();

        return userInfos;
    }


    /**
     * 通过产品 product_id 产品
     */
    public GoodsBean getAProduct(String productid) {

        if (TextUtils.isEmpty(productid)) {
            return null;
        }

        //关联数据库
        SQLiteDatabase database = helperDB.getWritableDatabase();

        String sql = "select * from " + ProductTable.TABLE_NAME +
                " where " + ProductTable.COL_PRODUCT_ID + "=?";

        Cursor cursor = database.rawQuery(sql, new String[]{productid});

        GoodsBean userInfo = null;//便于后边判空

        if (cursor.moveToNext()) {
            userInfo = new GoodsBean();

            userInfo.setName(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRODUCTNAME)));
            userInfo.setFigure(cursor.getString(cursor.getColumnIndex(ProductTable.COL_FIGURE)));
            userInfo.setNumber(cursor.getInt(cursor.getColumnIndex(ProductTable.COL_NUMBER)));
            userInfo.setCover_price(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRICE)));
            userInfo.setType(cursor.getString(cursor.getColumnIndex(ProductTable.COL_TYPE)));
            userInfo.setProduct_id(cursor.getString(cursor.getColumnIndex(ProductTable.COL_PRODUCT_ID)));
        }
        cursor.close();
        return userInfo;
    }


    /**
     * 通过 product_id 查询产品
     */
    public List<GoodsBean> getMoreProduct(List<String> products) {

        if (products == null || products.size() == 0) {

//            throw new NullPointerException("数据为空啊！！");
            return null;
        }

        List<GoodsBean> productmore = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            GoodsBean info = getAProduct(products.get(i));
            if (info != null) {
                productmore.add(info);
            }

        }


        return productmore;
    }


    /**
     * 保存单个产品
     */
    public void saveProduct(GoodsBean goodInfo) {

        if (goodInfo == null) {
            return;
        }

        //关联数据库
        SQLiteDatabase database = helperDB.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ProductTable.COL_FIGURE, goodInfo.getFigure());
        values.put(ProductTable.COL_NUMBER, goodInfo.getNumber());
        values.put(ProductTable.COL_PRICE, goodInfo.getCover_price());
        values.put(ProductTable.COL_PRODUCTNAME, goodInfo.getName());
        values.put(ProductTable.COL_TYPE, goodInfo.getType());
        values.put(ProductTable.COL_PRODUCT_ID, goodInfo.getProduct_id());

        database.replace(ProductTable.TABLE_NAME, null, values);
    }


    /**
     * 保存多个产品
     */
    public void saveMoreProduct(List<GoodsBean> userInfos ) {
        if (userInfos == null || userInfos.size() == 0) {

//            throw new NullPointerException("数据为空啊！！");
            return;
        }

        for (int i = 0; i < userInfos.size(); i++) {

            saveProduct(userInfos.get(i));

        }

    }


    /**
     * 删除产品
     */
    public void deleteProduct(String goodid ) {

        if(!TextUtils.isEmpty(goodid)) {
            //关联数据库
            SQLiteDatabase database = helperDB.getWritableDatabase();
            database.delete(ProductTable.TABLE_NAME,ProductTable.COL_PRODUCT_ID + "=?",new String[]{goodid});

        }

    }

    // 更新邀请状态
    public void updateProduct(GoodsBean bean){
        if (bean == null){
            return;
        }

        SQLiteDatabase database = helperDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.COL_NUMBER,bean.getNumber());
        database.update(ProductTable.TABLE_NAME,contentValues,
                ProductTable.COL_PRODUCT_ID+"=?",new String[]{bean.getProduct_id()});
    }
}
