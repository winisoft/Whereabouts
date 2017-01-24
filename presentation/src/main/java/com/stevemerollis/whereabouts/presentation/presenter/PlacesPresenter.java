package com.stevemerollis.whereabouts.presentation.presenter;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.domain.exception.DefaultErrorBundle;
import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.domain.interactor.DefaultObserver;
import com.stevemerollis.whereabouts.domain.interactor.GetPlaces;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.adapter.PlaceInfoWindowAdapter;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.mapper.PlaceModelDataMapper;
import com.stevemerollis.whereabouts.presentation.model.PlaceModel;
import com.stevemerollis.whereabouts.presentation.util.ResourcesUtil;
import com.stevemerollis.whereabouts.presentation.view.PlacesView;
import com.stevemerollis.whereabouts.presentation.view.fragment.PlacesFragment;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PlacesPresenter implements Presenter,
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private PlacesView placesView;

    private final GetPlaces getPlacesUseCase;
    private final PlaceModelDataMapper placeModelDataMapper;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    @Inject
    public PlacesPresenter(GetPlaces getPlacesUseCase, PlaceModelDataMapper placeModelDataMapper){
        this.getPlacesUseCase = getPlacesUseCase;
        this.placeModelDataMapper = placeModelDataMapper;
    }

    public void setView(@NonNull PlacesView placesView){
        this.placesView = placesView;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(placesView.context())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        ((PlacesFragment)this.placesView).getMapAsync(this);
    }

    @Override
    public void start() {
        if (mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void stop() {
        if (mGoogleApiClient != null){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void destroy() {
        this.getPlacesUseCase.dispose();
        this.placesView = null;
    }

    /**
     * Loads the places.
     */
    private void loadPlaces() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPlaceModelList();
    }

    private void showViewLoading() {
        this.placesView.showLoading();
    }
    private void hideViewLoading() {
        this.placesView.hideLoading();
    }
    private void showViewRetry() {
        this.placesView.showRetry();
    }
    private void hideViewRetry() {
        this.placesView.hideRetry();
    }
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.placesView.context(),
                errorBundle.getException());
        this.placesView.showError(errorMessage);
    }

    private void showPlacesCollectionInView(Collection<Place> placeCollection) {
        final Collection<PlaceModel> placeModelsCollection =
                this.placeModelDataMapper.transform(placeCollection);
        this.placesView.plotPlaceModels(placeModelsCollection);
    }

    private void getPlaceModelList() {
        PlaceRequestParams domainRequest = new PlaceRequestParams(0.0, 0.0, 123, "restaurant");
        this.getPlacesUseCase.execute(new PlaceListObserver(), GetPlaces.Params.forPlaces(domainRequest));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try{
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

            float z = ResourcesUtil.getFloat(placesView.context(), R.dimen.default_map_zoom);

            placesView.initMapPosition(mMap, latLng, z);
        } catch (SecurityException e){}

        loadPlaces();
    }
    @Override public void onConnectionSuspended(int i) {}
    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new PlaceInfoWindowAdapter(placesView.context()));
    }

    private final class PlaceListObserver extends DefaultObserver<List<Place>> {

        @Override
        public void onNext(List<Place> places) {
            PlacesPresenter.this.showPlacesCollectionInView(places);
        }

        @Override public void onComplete() {
            PlacesPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            PlacesPresenter.this.hideViewLoading();
            PlacesPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            PlacesPresenter.this.showViewRetry();
        }
    }

    public GoogleMap getMap(){
        return this.mMap;
    }
}
