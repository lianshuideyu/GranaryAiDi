package com.atguigu.granaryaidi.cart;

import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    public static CartStorage instance;
    private static Context mContext;
    /**
     * 数据存储在内存中
     */
    private SparseArray<GoodsBean> sparseArray;


    private HelperManager helperManager;

    private CartStorage() {
        //初始化数据库管理
        if(helperManager != null) {
            helperManager.closeDB();
        }
        /*
        初始化数据库管理,登录成功后和 广告界面跳转至主页面处初始化
        登录用户名先写死，-->zjc
         */
        helperManager = new HelperManager(mContext, "zjc");


        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    /**
     * 返回 数据库的管理类
     * @return
     */
    public HelperManager getHelperManager() {
        return helperManager;
    }

    private void listTosparseArray() {
        //得到所有数据
        List<GoodsBean> datas = getAllData();
        if(datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

            }
        }

    }

    /**
     * 得到所有数据
     *
     * @return
     */
    public List<GoodsBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private List<GoodsBean> getLocalData() {
//        ArrayList<GoodsBean> datas = new ArrayList<>();
//        //json数据
//        String saveJson = CacheUtils.getString(mContext, JSON_CART);
//        if (!TextUtils.isEmpty(saveJson)) {
//            //把保存的json数据解析成ArrayList数组
//            datas = new Gson().fromJson(saveJson, new TypeToken<ArrayList<GoodsBean>>() {
//            }.getType());
//        }

        /**
         * 从数据库获取全部 商品
         */
        List<GoodsBean> products = helperManager.getProductDAO().getProducts();
        if(products != null && products.size() > 0) {
            return products;
        }

        return null;
    }


    /**
     * 得到CartStorage
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            //加上锁
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage();
                }
            }
        }

        return instance;
    }

    /**
     * 添加数据
     * @param bean
     */
    public void addData(GoodsBean bean) {
        //查看内容中是否存在
        GoodsBean temp = sparseArray.get(Integer.parseInt(bean.getProduct_id()));
        if (temp != null) {
            //存在，就修改
            temp.setNumber(bean.getNumber()+temp.getNumber());
            //修改的情况--只可能为改产品的数量
            helperManager.getProductDAO().updateProduct(temp);
        } else {
            //如果不存在，保存到内存中
            temp = bean;
            helperManager.getProductDAO().saveProduct(temp);
        }

        //内存中更新
        sparseArray.put(Integer.parseInt(temp.getProduct_id()), temp);

        //同步到本地
//        commit();
    }


    /**
     * 删除数据
     *
     * @param bean
     */
    public void deleteData(GoodsBean bean) {
        //内存中更新
        sparseArray.delete(Integer.parseInt(bean.getProduct_id()));
        //同步到本地
//        commit();
        helperManager.getProductDAO().deleteProduct(bean.getProduct_id());
    }


    /**
     * 修改数据
     *
     * @param bean
     */
    public void updateData(GoodsBean bean) {
        //内存中更新
        sparseArray.put(Integer.parseInt(bean.getProduct_id()),bean);
        //同步到本地
//        commit();
        helperManager.getProductDAO().updateProduct(bean);
    }

    /**
     * 在本地保存一份
     */
    private void commit() {
        //把SparseArray 转换成List集合
        ArrayList<GoodsBean> goodsBeens = sparseArrayToList();
        //使用Gson把List集合转换成json的String数据
//        String json = new Gson().toJson(goodsBeens);
//        //把文本保存到sp中
//        CacheUtils.putString(mContext,JSON_CART,json);
        helperManager.getProductDAO().saveMoreProduct(goodsBeens);

    }

    private ArrayList<GoodsBean> sparseArrayToList() {
        ArrayList<GoodsBean> goodsBeens = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeens.add(goodsBean);
        }
        return goodsBeens;
    }
}
