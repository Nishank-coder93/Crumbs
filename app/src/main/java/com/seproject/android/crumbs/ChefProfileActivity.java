package com.seproject.android.crumbs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ChefProfileActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_profile_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /* Customized Rating Bar
        RatingBar rb_customColor = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) rb_customColor
                .getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#FEC400"),
                PorterDuff.Mode.SRC_ATOP); // for filled stars
        stars.getDrawable(1).setColorFilter(Color.parseColor("#FEC400"),
                PorterDuff.Mode.SRC_ATOP); // for half filled stars
        stars.getDrawable(0).setColorFilter(Color.parseColor("#D7D7D9"),
                PorterDuff.Mode.SRC_ATOP); // for empty stars

        /* End of customized rating bar */

        /* Grid View Activity for Photos */

        /* On close operation go back to map */
         ImageButton CloseButton = (ImageButton) findViewById(R.id.chef_close_button);
         CloseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context,MapsViewActivity.class);
                startActivity(intent);

            }

        });
        /* End of close functionality*/
        /* Grid View */
        ExpandableHeightGridView gridview = (ExpandableHeightGridView) findViewById(R.id.photoGridView);

        gridview.setExpanded(true);


        /* Custom adaptor ImageAdapter is the source for all items to be displayed in the grid */
        gridview.setAdapter(new PhotoImageAdapter(this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), PhotoSingleImageView.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        /* End of Grid view activity for photos */


        /* Grid View Activity for Menu */
        /* Grid View */
        ExpandableHeightGridView menugridview = (ExpandableHeightGridView) findViewById(R.id.dishMenuGridView);

        menugridview.setExpanded(true);


        /* Custom adaptor ImageAdapter is the source for all items to be displayed in the grid */
        menugridview.setAdapter(new DishImageAdapter(this));


        menugridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), DishViewerActivity.class);

                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        /* End of Grid view activity for Menu */
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng UC = new LatLng(32.7316535, -97.11099790000003);
        mMap.addMarker(new MarkerOptions().position(UC).title("UniversityCenter"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UC));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Intent intent = new Intent(context, DirectionToLocationActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}