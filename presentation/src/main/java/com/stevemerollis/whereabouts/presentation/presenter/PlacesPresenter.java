package com.stevemerollis.whereabouts.presentation.presenter;

import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.domain.exception.DefaultErrorBundle;
import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.domain.interactor.DefaultObserver;
import com.stevemerollis.whereabouts.domain.interactor.GetPlaces;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.mapper.PlaceModelDataMapper;
import com.stevemerollis.whereabouts.presentation.model.PlaceModel;
import com.stevemerollis.whereabouts.presentation.view.PlacesView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PlacesPresenter implements Presenter {

    private PlacesView placesView;

    private final GetPlaces getPlacesUseCase;
    private final PlaceModelDataMapper placeModelDataMapper;

    @Inject
    public PlacesPresenter(GetPlaces getPlacesUseCase, PlaceModelDataMapper placeModelDataMapper){
        this.getPlacesUseCase = getPlacesUseCase;
        this.placeModelDataMapper = placeModelDataMapper;
    }

    public void setView(@NonNull PlacesView placesView){
        this.placesView = placesView;
    }

    @Override
    public void start() {}

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void stop() {}

    @Override
    public void destroy() {
        this.getPlacesUseCase.dispose();
        this.placesView = null;
    }

    public void initialize(){ this.loadPlaces(); }

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
}
