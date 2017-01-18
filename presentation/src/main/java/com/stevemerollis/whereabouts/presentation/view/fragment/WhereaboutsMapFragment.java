package com.stevemerollis.whereabouts.presentation.view.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stevemerollis.whereabouts.presentation.di.components.PlaceComponent;
import com.stevemerollis.whereabouts.presentation.model.PlaceModel;
import com.stevemerollis.whereabouts.presentation.presenter.PlacesPresenter;
import com.stevemerollis.whereabouts.presentation.view.PlacesView;
import com.stevemerollis.whereabouts.presentation.view.activity.MapsActivity;

import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;

public class WhereaboutsMapFragment extends BaseMapFragment implements PlacesView {

    @Inject
    PlacesPresenter placesPresenter;

    public WhereaboutsMapFragment(){ setRetainInstance(true); }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PlaceComponent.class).inject(this);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.placesPresenter.setView(this);
    }

    @Override public void onResume() {
        super.onResume();
        this.placesPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.placesPresenter.pause();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.placesPresenter.destroy();
    }

    @Override public void onDetach() {
        super.onDetach();
        this.placesPresenter = null;
    }

    public void loadPlaces() {
        this.placesPresenter.initialize();
    }

    @Override
    public void plotPlaceModels(Collection<PlaceModel> placeModelCollection) {

        GoogleMap map = ((MapsActivity)getActivity()).getMap();

        for(PlaceModel model : placeModelCollection){
            LatLng latLng = new LatLng(model.getLatitude(), model.getLongitude());
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(model.getMarkerColor()))
                    .title(String.format(Locale.getDefault(), "%s : %s", model.getName(), model.getVicinity()));
            map.addMarker(options);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    public void initMapPosition(GoogleMap googleMap, Location lastLocation){
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()), 13));
    }
}
