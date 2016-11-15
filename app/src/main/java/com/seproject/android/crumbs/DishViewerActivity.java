package com.seproject.android.crumbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.google.android.gms.analytics.internal.zzy.ct;


/**
 * This is the Dish Viewer activity which controls the Dish info page
 */

public class DishViewerActivity extends Activity {
    final Context context = this;
    private int quantOfDish;

    DishViewerActivity() { quantOfDish = 0; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_info_viewer); // Setting the content layout dish_info_viewer.xml

        //initialises the product model and controller to handle the product data
        final ProductController prodCt = (ProductController) getApplicationContext();
        ModelProducts products = null;

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        DishImageAdapter dishAdapter = new DishImageAdapter(this);

        //Sets the image of the Dish in indvidual Dish profile
        ImageView imageView = (ImageView) findViewById(R.id.dish_cover_image);
        imageView.setImageResource(dishAdapter.mThumbIds[position]);

        //Sets the name of the Dish in Dish profile
        TextView profileName = (TextView) findViewById(R.id.dish_profile_name);
        String DishName = dishAdapter.mThumbNames[position];
        profileName.setText(DishName);

        //Retrieves and sets the price of the Dish in Dish Profile
        TextView price = (TextView) findViewById(R.id.dish_cost);
        int DishPrice = dishAdapter.mDishPrice[position];
        price.setText("$" + DishPrice);


        products = new ModelProducts(DishName, DishPrice);
        prodCt.setProducts(products);

        int productsize = prodCt.getProductArraylistsize();
        for (int j = 0; j < productsize; j++) {
            String pName = prodCt.getProducts(j).getProductName();
            int pPrice = prodCt.getProducts(j).getProductPrice();


            final int index = j;
            final Button btn1 = (Button) findViewById(R.id.add_to_cart);
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// TODO Auto-generated method stub
                    Log.i("TAG", "index:" + index);
                    ModelProducts productsObject = prodCt.getProducts(index);
                    if (!prodCt.getCart().CheckProductInCart(productsObject)) {
                        prodCt.getCart().setProducts(productsObject);
                        Toast.makeText(getApplicationContext(), "New CartSize:" + prodCt.getCart().getCartsize(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Products" + (index + 1) + "Already Added", Toast.LENGTH_LONG).show();
                    }
                    Intent intent = new Intent(context, ChefProfileActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This Method adds coffee number to the   qauntity
     */
    public void addQuantDish(View view){
        if(quantOfDish == 2){
            Toast.makeText(getApplicationContext(), " Cannot Order More Than Available ", Toast.LENGTH_LONG).show();
        }
        else{
            quantOfDish += 1;
            displayQuantity(quantOfDish);
        }
    }

    /**
     * This Method adds coffee number to the   qauntity
     */
    public void subQuantDish(View view){
        if(quantOfDish == 0){
            Toast.makeText(getApplicationContext(), " Dish Not Available ", Toast.LENGTH_LONG).show();
        }
        else{
            quantOfDish -= 1;
            displayQuantity(quantOfDish);
        }
    }

}
