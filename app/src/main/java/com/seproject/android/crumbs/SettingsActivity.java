package com.seproject.android.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import static com.seproject.android.crumbs.R.id.buttonLogout;

/**
 * Created by Mac-NB on 01/12/16.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogout;
    private Button buttonRegisterSeller;
    private Button buttonRegisterEvent;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        firebaseAuth = FirebaseAuth.getInstance();

        //Register Seller button intialisation
        buttonRegisterSeller = (Button) findViewById(R.id.registerSellerButton);
        buttonRegisterSeller.setOnClickListener(this);

        //Register Event button intialisation
        buttonRegisterEvent = (Button) findViewById(R.id.registerEventManager);
        buttonRegisterEvent.setOnClickListener(this);

        //Logout button intialisation
        //buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //buttonLogout.setOnClickListener(this);

        //Logout button intialisation
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);



    }

    //Single function to handle the onclick activity
    @Override
    public void onClick(View v) {
        if (v == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, SigninMainActivity.class));
        }
        else if (v == buttonRegisterSeller){
            startActivity(new Intent(this, SellerRegistrationActivity.class));
        }
        else if (v == buttonRegisterEvent){
            startActivity(new Intent(this, EventRegistrationActivity.class));
        }
    }
}
