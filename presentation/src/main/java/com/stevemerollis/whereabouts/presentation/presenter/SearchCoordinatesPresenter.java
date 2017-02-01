package com.stevemerollis.whereabouts.presentation.presenter;

import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.view.GeocodingView;

import javax.inject.Inject;

@PerActivity
public class SearchCoordinatesPresenter implements Presenter {

    private GeocodingView geocodingView;
    //private final GetCoordinates getCoordinatesUseCase;

    /*@Inject public SearchCoordinatesPresenter(GetCoordinates getCoordinatesUseCase) {
        this.getCoordinatesUseCase = getCoordinatesUseCase;
    }*/

    @Inject public SearchCoordinatesPresenter() {

    }

    public void setView(@NonNull GeocodingView geocodingView) {
        this.geocodingView = geocodingView;
    }

    @Override public void start() {}
    @Override public void resume() {}
    @Override public void pause() {}
    @Override public void stop() {}
    @Override public void destroy() {}

    private void showViewLoading() {
        this.geocodingView.showLoading();
    }
    private void hideViewLoading() {
        this.geocodingView.hideLoading();
    }
    private void showViewRetry() {
        this.geocodingView.showRetry();
    }
    private void hideViewRetry() {
        this.geocodingView.hideRetry();
    }
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.geocodingView.context(),
                errorBundle.getException());
        this.geocodingView.showError(errorMessage);
    }

    public void findCoordinates() {

    }
}
