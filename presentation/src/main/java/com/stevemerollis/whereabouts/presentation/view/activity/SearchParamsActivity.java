package com.stevemerollis.whereabouts.presentation.view.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
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

    public static final int GEOCODING_REQUEST_CODE = 1;

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


    protected void showDialogFragment(DialogFragment geocodingFragment, int requestCode) {

        Fragment f = this.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        SearchParamsFragment spf = (SearchParamsFragment)f;

        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        geocodingFragment.setTargetFragment(spf, requestCode);
        geocodingFragment.show(fragmentTransaction, "dialog");
    }

    @Override
    public void onGoBtnClicked(PlaceRequestParams placeRequestParams) {
        this.navigator.navigateToMapsActivity(this, placeRequestParams);
    }

    @Override
    public void onRadButNearPlaceCheckedChanged() {
        showDialogFragment(new GeocodingFragment(), GEOCODING_REQUEST_CODE);
    }
}
