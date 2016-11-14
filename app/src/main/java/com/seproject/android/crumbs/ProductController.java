package com.seproject.android.crumbs;

import android.app.Application;

import java.util.ArrayList;

/**
 * This is the main controller that is used for setting and retrieving dish products
 * from the menu and setting it up to be retrieved by other classes
 */

public class ProductController extends Application {
    private ArrayList<ModelProducts> myproducts = new ArrayList<ModelProducts>();
    private ModelCart myCart = new ModelCart();
    public ModelProducts getProducts(int pPosition){
        return myproducts.get(pPosition);
    }
    public void  setProducts(ModelProducts products){
        myproducts.add(products);
    }
    public ModelCart getCart(){
        return myCart;
    }
    public int  getProductArraylistsize(){
        return myproducts.size();
    }
}
