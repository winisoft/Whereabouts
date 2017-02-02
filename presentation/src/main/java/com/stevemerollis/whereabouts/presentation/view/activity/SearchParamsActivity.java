package com.stevemerollis.whereabouts.presentation.view.activity;

import android.os.Bundle;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerSearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.di.components.SearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;
import com.stevemerollis.whereabouts.presentation.view.fragment.GeocodingFragment;
import com.stevemerollis.whereabouts.presentation.view.fragment.SearchParamsFragment;

import butterknife.OnClick;

public class SearchParamsActivity extends BaseActivity implements HasComponent<SearchParamsComponent>,
    SearchParamsFragment.SearchParamsListener {

    private SearchParamsComponent searchParamsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.initializeInjector();

        if (savedInstanceState == null){
            addFragment(R.id.fragmentContainer, new SearchParamsFragment());
        }
    }

    @Override
    public SearchParamsComponent getComponent() {
        return this.searchParamsComponent;
    }

    private void initializeInjector() {
        this.searchParamsComponent = DaggerSearchParamsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public void onGoBtnClicked(PlaceRequestParams placeRequestParams) {
        this.navigator.navigateToMapsActivity(this, placeRequestParams);
    }

    @Override
    public void onRadButNearPlaceCheckedChanged() {
        showDialogFragment(new GeocodingFragment());
    }
}
