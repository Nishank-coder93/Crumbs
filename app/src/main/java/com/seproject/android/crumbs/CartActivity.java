package com.seproject.android.crumbs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This is a simple cart activity
 * Here we retrieve the products and present it in a cart
 */

public class CartActivity extends AppCompatActivity {

    int totalprice;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //initialise Linear layout viee cartContent from activity_cart.xml
        LinearLayout cartContent = (LinearLayout) findViewById(R.id.dish_item_holder);

        //Retrieve TextViews from activity_cart.xml
        TextView pricetext=(TextView)findViewById(R.id.total_amount);
        TextView itemtext= (TextView)findViewById(R.id.num_cart_items);

        final ProductController ct = (ProductController) getApplicationContext();
        final int CartSize = ct.getCart().getCartsize();  // Gets the size of the cart


        String pName = "";
        int pPrice = 0;

        for(int i=0;i<CartSize;i++)
        {
            LinearLayout itemDescription = new LinearLayout(this);

        /* item description linear layout settings */
            itemDescription.setOrientation(LinearLayout.HORIZONTAL); //Horizontal Orientation
            itemDescription.setBackgroundColor(Color.parseColor("#ffffff")); //Set color
            //Set Height, Width and weight of Linear Layout view
            itemDescription.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            itemDescription.setPadding(16,0,0,0); // Padding
        /* End of linear layout settings */

        /* Name of the Dish Text view settings */
            TextView DishName = new TextView(this);
            DishName.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            DishName.setGravity(Gravity.CENTER);
            DishName.setTextColor(Color.parseColor("#000000"));
            DishName.setTextSize(20);
            DishName.setPadding(6,6,6,6);
        /* End of Text view settings */

        /* Price of the Dish text view settings */
            TextView DishPrice = new TextView(this);
            DishPrice.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            DishPrice.setGravity(Gravity.CENTER);
            DishPrice.setTextColor(Color.parseColor("#000000"));
            DishPrice.setTextSize(20);
            DishPrice.setPadding(6,6,6,6);
        /* End of Text view settings */

            pName = ct.getCart().getProducts(i).getProductName(); // Retrieve the name of the Dish from Product controller
            pPrice = ct.getCart().getProducts(i).getProductPrice(); // Retrieve the price of the Dish from product controller

            DishName.setText(pName);    // Set the text of the Dish Name text view to pName
            DishPrice.setText("$" + pPrice);  // Set the text of the Price of Dish to pPrice
            itemDescription.addView(DishName); // Add TextView DishName to Linear layout ItemDescription
            itemDescription.addView(DishPrice); // Add textView DishPrice to Linear Layout ItemDescription
            cartContent.addView(itemDescription); // Add the whole ItemDescription Linear Layout to Linear Layout view cartContent

        }

        for(int j=0;j<CartSize;j++){
            int  eachitemPrice = ct.getCart().getProducts(j).getProductPrice();
            totalprice=totalprice+eachitemPrice;
        }


        pricetext.setText("$" + totalprice);
        itemtext.setText(String.valueOf(CartSize));



        // place order

        Button placeOrder = (Button) findViewById(R.id.place_order);

        placeOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,OrderPlacedActivity.class);
                startActivity(intent);

            }

        });

    }
}
