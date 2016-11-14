package com.seproject.android.crumbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SellerToasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_map_activity_layout);
        Toast.makeText(getApplicationContext(),
                "Chef request pending.Wait for verification", Toast.LENGTH_LONG).show();
    }
}
