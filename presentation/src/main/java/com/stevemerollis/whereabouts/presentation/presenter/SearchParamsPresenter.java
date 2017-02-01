package com.stevemerollis.whereabouts.presentation.presenter;

import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.widget.TimePicker;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.domain.PlaceType;
import com.stevemerollis.whereabouts.domain.exception.DefaultErrorBundle;
import com.stevemerollis.whereabouts.domain.exception.ErrorBundle;
import com.stevemerollis.whereabouts.domain.interactor.DefaultObserver;
import com.stevemerollis.whereabouts.domain.interactor.GetPlaceTypes;
import com.stevemerollis.whereabouts.presentation.Enums;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.exception.ErrorMessageFactory;
import com.stevemerollis.whereabouts.presentation.mapper.PlaceTypeModelDataMapper;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;
import com.stevemerollis.whereabouts.presentation.view.SearchParamsView;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

@PerActivity
public class SearchParamsPresenter implements Presenter, TimePickerDialog.OnTimeSetListener {

    private SearchParamsView searchParamsView;
    private final GetPlaceTypes getPlaceTypesUseCase;
    private final PlaceTypeModelDataMapper placeTypeModelDataMapper;

    private int openAtHour;
    private int openAtMinute;

    @Inject
    public SearchParamsPresenter(GetPlaceTypes getPlaceTypesUseCase, PlaceTypeModelDataMapper placeTypeModelDataMapper){
        this.getPlaceTypesUseCase = getPlaceTypesUseCase;
        this.placeTypeModelDataMapper = placeTypeModelDataMapper;
    }

    public void setView(@NonNull SearchParamsView searchParamsView){
        this.searchParamsView = searchParamsView;
    }

    @Override public void start() {}
    @Override public void resume() {}
    @Override public void pause() {}
    @Override public void stop() {}
    @Override public void destroy() {}

    private void showViewLoading() {
        this.searchParamsView.showLoading();
    }
    private void hideViewLoading() {
        this.searchParamsView.hideLoading();
    }
    private void showViewRetry() {
        this.searchParamsView.showRetry();
    }
    private void hideViewRetry() {
        this.searchParamsView.hideRetry();
    }
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.searchParamsView.context(),
                errorBundle.getException());
        this.searchParamsView.showError(errorMessage);
    }

    public void loadPlaceTypes(){
        this.hideViewRetry();
        this.showViewLoading();
        this.getPlaceTypesList();
    }

    private void showPlaceTypeCollectionInView(Collection<PlaceType> placeTypeCollection) {
        final Collection<PlaceTypeModel> placeTypeModelCollection =
                this.placeTypeModelDataMapper.transform(placeTypeCollection);
        this.searchParamsView.renderPlaceTypesList(placeTypeModelCollection);
    }

    private void getPlaceTypesList() {
        this.getPlaceTypesUseCase.execute(new PlaceTypeListObserver(), null);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.openAtHour = hourOfDay;
        this.openAtMinute = minute;

        boolean am = openAtHour < 13;
        String openAtTime = MessageFormat.format(this.searchParamsView.context().getString(R.string.spf_rb_open_at_time_text),
                am ? openAtHour : (openAtHour - 12), openAtMinute, am ? "a.m." : "p.m.");
        this.searchParamsView.setOpenAtTime(openAtTime);
    }

    public int getOpenAtHour() {
        return openAtHour;
    }

    public int getOpenAtMinute() {
        return openAtMinute;
    }

    public void bundleParams(List<String> selectedPlaceTypes, String distanceText, Enums.VISITED_PARAM visitedParam, int minRating,
                             boolean myRate, int maxPrice, boolean usesOpenAtTime){

        PlaceRequestParams params = new PlaceRequestParams();
        params.placeTypes = selectedPlaceTypes;


    }

    public void onGoBtnClicked() {
        //TODO: gather the parameters from the views here
        PlaceRequestParams params = new PlaceRequestParams();
        this.searchParamsView.requestSearch(params);
    }

    public void onBtnOpenAtCheckedChanged() {

    }

    private final class PlaceTypeListObserver extends DefaultObserver<List<PlaceType>> {
        @Override
        public void onNext(List<PlaceType> placeTypes) {
            showPlaceTypeCollectionInView(placeTypes);
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
