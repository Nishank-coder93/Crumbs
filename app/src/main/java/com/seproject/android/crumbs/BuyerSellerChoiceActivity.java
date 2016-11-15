package com.seproject.android.crumbs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
* This is view for selecting to register as a buyer or a seller
* */

public class BuyerSellerChoiceActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_seller_choice_layout);
        addListenerOnUserButton();
        addListenerOnSellerRegButton();
        addListenerOnEventRegButton();
    }

    Button asUserButton;   // Initialise the user button
    Button asSellerButton; // Initialise the Buyer button
    Button asEventButton; // Initialise the Event organiser button


// on click activity opens the Map View if selected as a user
    public void addListenerOnUserButton() {

        final Context context = this;

        asUserButton = (Button) findViewById(R.id.continueUserButton);

        asUserButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,MapsViewActivity.class);
                startActivity(intent);

            }

        });

    }

    //On click activity opens up the seller registration if selected as a seller
    public void addListenerOnSellerRegButton() {

        final Context context = this;

        asSellerButton = (Button) findViewById(R.id.sellerRegisterButton);

        asSellerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, SellerRegistrationActivity.class);
                startActivity(intent);

            }

        });

    }


    //On click activity opens up the seller registration if selected as a seller
    public void addListenerOnEventRegButton() {

        final Context context = this;

        asEventButton = (Button) findViewById(R.id.eventOrganiserButton);

        asEventButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, EventRegistrationActivity.class);
                startActivity(intent);

            }

        });

    }
}
