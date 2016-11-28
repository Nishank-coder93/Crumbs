package com.seproject.android.crumbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import static android.R.attr.data;


public class SellerRegistrationActivity extends FragmentActivity implements View.OnClickListener {

    /*
    * This is the most important part of the application
    * This activity registers new chefs
    * Here they feed in their details like Experience, Address
    * They also input their menu as a first time setup
    * */

    private  TextView chefName;
    private ImageButton mSetupImageButton;
    private String mFullNameString;
    private String mImageUrlString;
    private EditText mExperience;
    private EditText mNumber;
    private EditText mCuisine;
    private EditText mWebsite;
    private EditText mAddress;

    private static final int GALLERY_REQUEST = 1;

    String mDefaultImageString = "http://www.oldpotterybarn.co.uk/wp-content/uploads/2015/06/default-medium.png";
    private Uri mImageUri = null;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabaseUsers;
    DatabaseReference mDatabaseUserFullName;
    DatabaseReference mDatabaseUserImageUri;

    private StorageReference mStorageImage;

    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_profile_registration_layout);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        mStorageImage = FirebaseStorage.getInstance().getReference().child("Profile_images");

        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        mSetupImageButton = (ImageButton) findViewById(R.id.user_profile_photo);

        chefName = (TextView) findViewById(R.id.user_profile_name);

        mExperience = (EditText) findViewById(R.id.edit_experience);
        mNumber = (EditText) findViewById(R.id.edit_call_number);
        mCuisine = (EditText) findViewById(R.id.edit_cusine);
        mWebsite = (EditText) findViewById(R.id.edit_website_link);
        mAddress = (EditText) findViewById(R.id.edit_address);

        mDatabaseUserFullName = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("fullName");
        mDatabaseUserFullName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mFullNameString = dataSnapshot.getValue().toString();
                chefName.setText(mFullNameString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mSubmitButton = (Button) findViewById(R.id.submitChefProfile);

        mDatabaseUserImageUri = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("image");

        if(mDatabaseUserImageUri == null) {
            Picasso.with(SellerRegistrationActivity.this).load(mDefaultImageString).into(mSetupImageButton);
            Log.d("Image"," So the default image loaded ");
        }
        else {
            mDatabaseUserImageUri.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue()!=null) {
                        mImageUrlString = dataSnapshot.getValue().toString();
                        Picasso.with(SellerRegistrationActivity.this).load(mImageUrlString).into(mSetupImageButton);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSetupAccount();

            }
        });

        mSetupImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });

    }

    private void startSetupAccount() {

        final String userId = mAuth.getCurrentUser().getUid();

        final String experience = mExperience.getText().toString().trim();
        final String number = mNumber.getText().toString().trim();
        final String cuisine = mCuisine.getText().toString().trim();
        final String website = mWebsite.getText().toString().trim();
        final String address = mAddress.getText().toString().trim();

        Log.d("just Debug ","hello There 123123123123123123");

        if(mImageUri != null) {

            StorageReference filepath = mStorageImage.child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Log.d("Success","Successful in entering to DATABASE");
                    String downloadUriString = taskSnapshot.getDownloadUrl().toString();

                    System.out.print("Profile pid "+downloadUriString);

                    mDatabaseUsers.child(userId).child("image").setValue(mAuth.getCurrentUser().getUid());
                    mDatabaseUsers.child(userId).child("image").setValue(downloadUriString);

                    mDatabaseUsers.child(userId).child("experience").setValue(experience);
                    mDatabaseUsers.child(userId).child("number").setValue(number);
                    mDatabaseUsers.child(userId).child("cuisine").setValue(cuisine);
                    mDatabaseUsers.child(userId).child("website").setValue(website);
                    mDatabaseUsers.child(userId).child("address").setValue(address);

                    Toast.makeText(SellerRegistrationActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MapsViewActivity.class));

                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    //.setAspectRatio(1, 1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();

                mSetupImageButton.setImageURI(mImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    @Override
    public void onClick(View v) {

    }

}
