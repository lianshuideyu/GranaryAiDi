package com.atguigu.granaryaidi.cart;

import android.content.Context;

import com.atguigu.granaryaidi.cart.dao.ProductDAO;
import com.atguigu.granaryaidi.cart.db.HelperDB;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HelperManager {


    private final HelperDB helper;
    private ProductDAO productDAO;

    /**
     * 初始化 联系人 及消息的 数据库
     * @param context
     * @param name
     */
    public HelperManager(Context context, String name) {
        helper = new HelperDB(context, name + ".db");

        productDAO = new ProductDAO(helper);

    }

    /**
     * 关闭数据库
     */
    public void closeDB() {

        if (helper != null) {

            helper.close();
        }
    }

    public HelperDB getHelper() {
        return helper;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

}
