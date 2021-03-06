package com.seproject.android.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;

import static android.R.attr.password;

/*
* User Registeration Activity
* This activity is for Register users to the Database (Firebase). A database platform provided by Google
* For authorizing a User, Maintaining their data in JSON format
* New users will register themselves here by giving their credentials
* */

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextFirstName;  // An EditText type for First Name
    private EditText editTextLastName;  // An EditText type for Last Name
    private EditText editTextEmail;   // An EditText type for Email
    private EditText editTextUserName; // An EditText type for username
    private EditText editTextPassword; // An EditText password type for Password
    private EditText editTextPassword1; // An EditText Password type for confirming password

    //private EditText editTextExperience;
    //private EditText editTextCuisine;
    //private NumberFormat editPhone;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;

    /* Setting the variable to access the Firebase (Database) reference of the user for authorization */
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_register_user_main);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");

        if(firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), BuyerSellerChoiceActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextUserName = (EditText) findViewById(R.id.editUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        //textViewSignIn = (TextView) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
        //textViewSignIn.setOnClickListener(this);
    }

    //Registration method
    private void registerUser() {
        final String firstName = editTextFirstName.getText().toString().trim();
        final String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        final String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password1 = editTextPassword1.getText().toString().trim();

        if(TextUtils.isEmpty(firstName)) {
            Toast.makeText(this, "Please enter first yout name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter an email-id", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(password.equals(password1))) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            /* Gets the User Id of the current user where the information about the user will be stored
                            * we are storing First Name, Last Name and A Full Name attribute */
                            String userId = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = databaseReference.child(userId);
                            current_user_db.child("firstName").setValue(firstName);
                            current_user_db.child("lastName").setValue(lastName);
                            current_user_db.child("fullName").setValue(firstName+" "+lastName);
                            current_user_db.child("username").setValue(username);

                            user = FirebaseAuth.getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(firstName+" "+lastName)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            }
                                        }
                                    });

                            Toast.makeText(RegisterUserActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), BuyerSellerChoiceActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterUserActivity.this, "Could not register... Please try again", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), RegisterUserActivity.class));
                        }
                    }
                });
    }

    /*private void updateUserData() {
        String phone = editPhone.getText().toString().trim();

        if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
            return;
        }
    }*/

    @Override
    public void onClick(View v) {
        if(v == buttonRegister) {
            registerUser();
            //updateUserData();
        }
    }
}
