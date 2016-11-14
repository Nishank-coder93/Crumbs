package com.seproject.android.crumbs;

import java.util.ArrayList;

/**
 * Basic Model cart which stores the information of the items in cart.
 * Retrieves the data from the ModelProducts class which keeps track of the dishes selected
 */

public class ModelCart {
    //initialise an array list to hold the information on dishes selected
    private ArrayList<ModelProducts> cartItems = new ArrayList<ModelProducts>();
    public ModelProducts getProducts(int position){
        return cartItems.get(position);
    } //gets the product info
    public void setProducts(ModelProducts Products){
        cartItems.add(Products);
    } //adds the prodcut in the cart

    // returns the cart size TODO Edit cart
    public int getCartsize(){

        return cartItems.size();
    }

    // returns the items present in the cart
    public boolean CheckProductInCart(ModelProducts aproduct){
        return cartItems.contains(aproduct);
    }

}
