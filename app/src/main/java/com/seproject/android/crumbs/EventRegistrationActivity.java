package com.seproject.android.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mac-NB on 14/11/16.
 */

public class EventRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_profile_registration_layout);
        init();

    }

    public Button button;
    public void init(){

        button=(Button)findViewById(R.id.submitChefProfile);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent register= new Intent(EventRegistrationActivity.this,SellerToasterActivity.class);
                startActivity(register);
            }
        });
    }
}
