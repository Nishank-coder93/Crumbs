package com.seproject.android.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class SellerRegistrationActivity extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_profile_registration_layout);
        init();

    }

    public Button button;
    public void init(){

        button=(Button)findViewById(R.id.submitChefProfile);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent register= new Intent(SellerRegistrationActivity.this,SellerToasterActivity.class);
                startActivity(register);
            }
        });
    }

}
