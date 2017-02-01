package com.stevemerollis.whereabouts.presentation.presenter;

import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.domain.exception.DefaultErrorBundle;
import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.domain.interactor.DefaultObserver;
import com.stevemerollis.whereabouts.domain.interactor.GetGeocodingResults;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.view.GeocodingView;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class GeocodingPresenter implements Presenter{

    private GeocodingView geocodingView;
    private final GetGeocodingResults getGeocodingResultsUseCase;

    @Inject
    public GeocodingPresenter(GetGeocodingResults getGeocodingUseCase) {
        this.getGeocodingResultsUseCase = getGeocodingUseCase;
    }

    public void setView(@NonNull GeocodingView geocodingView) {
        this.geocodingView = geocodingView;
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

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

    public void geocode(String input) {
        this.getGeocodingResultsUseCase.execute(new GeocodingResultsObserver(), GetGeocodingResults.Params.forQuery(input));
    }

    private final class GeocodingResultsObserver extends DefaultObserver<List<GeocodingResult>> {
        @Override
        public void onNext(List<GeocodingResult> placeTypes) {
            String wait = "yes";
            //show
        }

        @Override public void onComplete() {
            hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }
    }
}
