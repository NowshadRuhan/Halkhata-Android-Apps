package com.appsticit1.myshop;

import java.text.SimpleDateFormat;

public class ModelClass {

    String product_Name;
    String buy_Price;
    String sell_Price;
    String mydataTime;



    public ModelClass(String product_Name, String buy_Price, String sell_Price, String mydataTime) {
        this.product_Name = product_Name;
        this.buy_Price = buy_Price;
        this.sell_Price = sell_Price;
        this.mydataTime = mydataTime;

    }




    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getBuy_Price() {
        return buy_Price;
    }

    public void setBuy_Price(String buy_Price) {
        this.buy_Price = buy_Price;
    }

    public String getSell_Price() {
        return sell_Price;
    }

    public void setSell_Price(String sell_Price) {
        this.sell_Price = sell_Price;
    }

    public String getmydataTime() {
        return mydataTime;
    }

    public void setmydataTime(String mydataTime) {
        this.mydataTime = mydataTime;
    }

}
