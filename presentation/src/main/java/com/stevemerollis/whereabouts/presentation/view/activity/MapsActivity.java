package com.stevemerollis.whereabouts.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerPlaceComponent;
import com.stevemerollis.whereabouts.presentation.di.components.PlaceComponent;
import com.stevemerollis.whereabouts.presentation.di.components.SearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.view.fragment.PlacesFragment;
import com.stevemerollis.whereabouts.presentation.view.fragment.SearchParamsFragment;

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

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MapsActivity.class);
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
