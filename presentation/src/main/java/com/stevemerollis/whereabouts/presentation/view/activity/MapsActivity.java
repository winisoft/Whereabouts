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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.adapter.PlaceInfoWindowAdapter;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerPlaceComponent;
import com.stevemerollis.whereabouts.presentation.di.components.PlaceComponent;
import com.stevemerollis.whereabouts.presentation.view.fragment.WhereaboutsMapFragment;

public class MapsActivity extends BaseActivity implements HasComponent<PlaceComponent> {

    private PlaceComponent placeComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.initializeInjector();

        if (savedInstanceState == null){
            addFragment(R.id.fragmentContainer, new WhereaboutsMapFragment());
        }
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
}
