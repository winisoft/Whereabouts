package com.stevemerollis.whereabouts.presentation.view.activity;

import android.os.Bundle;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerPlaceComponent;
import com.stevemerollis.whereabouts.presentation.di.components.PlaceComponent;
import com.stevemerollis.whereabouts.presentation.view.fragment.PlacesFragment;

public class MapsActivity extends BaseActivity implements HasComponent<PlaceComponent> {

    private PlaceComponent placeComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.initializeInjector();

        if (savedInstanceState == null){
            addFragment(R.id.fragmentContainer, new PlacesFragment());
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
