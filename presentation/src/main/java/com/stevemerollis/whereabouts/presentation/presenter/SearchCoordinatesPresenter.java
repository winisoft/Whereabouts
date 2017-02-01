package com.stevemerollis.whereabouts.presentation.presenter;

import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.view.SearchCoordinatesView;

import javax.inject.Inject;

@PerActivity
public class SearchCoordinatesPresenter implements Presenter {

    private SearchCoordinatesView searchCoordinatesView;
    //private final GetCoordinates getCoordinatesUseCase;

    /*@Inject public SearchCoordinatesPresenter(GetCoordinates getCoordinatesUseCase) {
        this.getCoordinatesUseCase = getCoordinatesUseCase;
    }*/

    @Inject public SearchCoordinatesPresenter() {

    }

    public void setView(@NonNull SearchCoordinatesView searchCoordinatesView) {
        this.searchCoordinatesView = searchCoordinatesView;
    }

    @Override public void start() {}
    @Override public void resume() {}
    @Override public void pause() {}
    @Override public void stop() {}
    @Override public void destroy() {}

    private void showViewLoading() {
        this.searchCoordinatesView.showLoading();
    }
    private void hideViewLoading() {
        this.searchCoordinatesView.hideLoading();
    }
    private void showViewRetry() {
        this.searchCoordinatesView.showRetry();
    }
    private void hideViewRetry() {
        this.searchCoordinatesView.hideRetry();
    }
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.searchCoordinatesView.context(),
                errorBundle.getException());
        this.searchCoordinatesView.showError(errorMessage);
    }

    public void findCoordinates() {

    }
}
