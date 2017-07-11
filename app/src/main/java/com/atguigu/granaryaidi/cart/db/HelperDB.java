package com.atguigu.granaryaidi.cart.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atguigu.granaryaidi.cart.table.ProductTable;

/**
 * Created by Administrator on 2017/7/11.
 */

public class HelperDB extends SQLiteOpenHelper{


    /**
     * 注意 name后边加.db
     * @param context
     * @param name
     */
    public HelperDB(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**
         * 创建 商品的数据库
         */
        sqLiteDatabase.execSQL(ProductTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
