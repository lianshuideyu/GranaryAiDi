package com.atguigu.granaryaidi.cart;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GoodsBean {


    /**
     * cover_price : 138.00
     * figure : /supplier/1478873740576.jpg
     * name : 【尚硅谷】日常 萌系小天使卫衣--白色款
     * product_id : 10659
     */

    private String type ;//商品的型号
    private String cover_price;
    private String figure;//图标地址
    private String name;//商品名
    private String product_id;//识别码
    private int number = 1;

    /**
     * 是否选中
     */
    private boolean isChecked =true;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public GoodsBean(String type, String cover_price, String figure, String name, String product_id, int number) {
        this.type = type;
        this.cover_price = cover_price;
        this.figure = figure;
        this.name = name;
        this.product_id = product_id;
        this.number = number;
    }

    public GoodsBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "type='" + type + '\'' +
                ", cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                '}';
    }
}
