package com.stevemerollis.whereabouts.presentation.view.activity;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerPlaceComponent;
import com.stevemerollis.whereabouts.presentation.di.components.PlaceComponent;
import com.stevemerollis.whereabouts.presentation.view.fragment.WhereaboutsMapFragment;

public class MapsActivity extends BaseActivity implements HasComponent<PlaceComponent>,
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private PlaceComponent placeComponent;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.initializeInjector();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        WhereaboutsMapFragment mapsFragment = (WhereaboutsMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapsFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        WhereaboutsMapFragment mapsFragment = (WhereaboutsMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapsFragment.loadPlaces();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try{
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (lastLocation != null) {
                WhereaboutsMapFragment mapsFragment = (WhereaboutsMapFragment) getFragmentManager().findFragmentById(R.id.map);
                mapsFragment.initMapPosition(mMap, lastLocation);
            }
        } catch (SecurityException e){}
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public PlaceComponent getComponent() {
        return this.placeComponent;
    }

    private void initializeInjector() {
        this.placeComponent = DaggerPlaceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public GoogleMap getMap(){
        return this.mMap;
    }
}
