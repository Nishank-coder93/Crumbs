package com.seproject.android.crumbs;

/**
 *
 * Basic container class for setting the product name & price for now
 */

public class ModelProducts {
    private String productName;
    //private String productDesc;
    private int productPrice;
    public ModelProducts(String productName,int productPrice){
        this.productName = productName;
        //this.productDesc = productDesc;
        this.productPrice = productPrice;
    }

    public String getProductName(){
        return productName;
    }



    public int getProductPrice(){
        return productPrice;
    }
}
