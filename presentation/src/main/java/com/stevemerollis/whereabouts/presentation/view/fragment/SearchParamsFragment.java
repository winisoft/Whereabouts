package com.stevemerollis.whereabouts.presentation.view.fragment;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.presentation.Enums;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.view.activity.SearchParamsActivity;
import com.stevemerollis.whereabouts.presentation.view.adapter.PlaceTypeModelAdapter;
import com.stevemerollis.whereabouts.presentation.di.components.SearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;
import com.stevemerollis.whereabouts.presentation.presenter.SearchParamsPresenter;
import com.stevemerollis.whereabouts.presentation.view.SearchParamsView;
import com.stevemerollis.whereabouts.presentation.view.adapter.PlaceTypesLayoutManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class SearchParamsFragment extends BaseFragment implements SearchParamsView {

    public interface SearchParamsListener {
        void onGoBtnClicked(final PlaceRequestParams placeRequestParams);
        void onRadButNearPlaceCheckedChanged();
    }

    @Inject SearchParamsPresenter searchParamsPresenter;
    @Inject public PlaceTypeModelAdapter placeTypeAmusementAdapter;
    @Inject public PlaceTypeModelAdapter placeTypeEatingAdapter;
    @Inject public PlaceTypeModelAdapter placeTypeShoppingAdapter;

    @Bind(R.id.spf_rv_amusement) RecyclerView rv_amusement;
    @Bind(R.id.spf_rv_eating) RecyclerView rv_eating;
    @Bind(R.id.spf_rv_shopping) RecyclerView rv_shopping;
    @Bind(R.id.spf_cb_visited) CheckBox cb_visited;
    @Bind(R.id.spf_cb_never_visited) CheckBox cb_never_visited;
    @Bind(R.id.spf_cb_marked_to_visit) CheckBox cb_marked_to_visit;
    @Bind(R.id.spf_et_distance) EditText et_distance;
    @Bind(R.id.spf_rg_near) RadioGroup rg_near;
    @Bind(R.id.spf_rb_near_me) RadioButton rb_near_me;
    @Bind(R.id.spf_rb_near_place) RadioButton rb_near_place;
    @Bind(R.id.spf_rg_open) RadioGroup rg_open;
    @Bind(R.id.spf_rb_open_at_time) RadioButton rb_open_time;
    @Bind(R.id.spf_r8_rating_me) RatingBar r8_rating;
    @Bind(R.id.spf_r8_price) RatingBar r8_price;
    @Bind(R.id.spf_btn_go) Button btn_go;

    private SearchParamsListener searchParamsListener;

    public SearchParamsFragment() { setRetainInstance(true); }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof SearchParamsListener) {
            this.searchParamsListener = (SearchParamsListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(SearchParamsComponent.class).inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SearchParamsActivity.GEOCODING_REQUEST_CODE) {
            String formattedAddress = data.getStringExtra(GeocodingFragment.FORMATTED_ADDRESS_EXTRA_KEY);
            if (formattedAddress != null && !formattedAddress.equals("")) {
                String radButText = MessageFormat.format(
                        getString(R.string.spf_rb_near_place_specified_text), formattedAddress);
                rb_near_place.setText(radButText);
            }
        }

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_search_params, container, false);
        ButterKnife.bind(this, fragmentView);

        prepareViews();

        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.searchParamsPresenter.setView(this);

        if (savedInstanceState == null){
            loadPlaceTypes();
        }
    }

    @Override public void onStart() {
        super.onStart();
        if (searchParamsPresenter != null){
            this.searchParamsPresenter.start();
        }
    }

    @Override public void onResume() {
        super.onResume();
        if (searchParamsPresenter != null){
            this.searchParamsPresenter.resume();
        }
    }

    @Override public void onPause() {
        super.onPause();
        if (searchParamsPresenter != null){
            this.searchParamsPresenter.pause();
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (searchParamsPresenter != null){
            this.searchParamsPresenter.destroy();
        }
    }

    @Override public void showLoading() {}
    @Override public void hideLoading() {}
    @Override public void showRetry() {}
    @Override public void hideRetry() {}
    @Override public void showError(String message) {}
    @Override public Context context() { return this.getActivity(); }

    @Override
    public void setOpenAtTime(String openAtTime) {
        rb_open_time.setText(openAtTime);
    }

    @Override
    public void renderPlaceTypesList(Collection<PlaceTypeModel> placeTypeModelsCollection) {
        if (placeTypeModelsCollection != null) {
            List<PlaceTypeModel> amusement = new ArrayList<>();
            List<PlaceTypeModel> eating = new ArrayList<>();
            List<PlaceTypeModel> shopping = new ArrayList<>();

            for (PlaceTypeModel placeType : placeTypeModelsCollection) {
                if (placeType.type.equals("amusement")) {
                    amusement.add(placeType);
                } else if (placeType.type.equals("eating")) {
                    eating.add(placeType);
                } else if (placeType.type.equals("shopping")) {
                    shopping.add(placeType);
                }
            }

            this.placeTypeAmusementAdapter.setPlaceTypeModelCollection(amusement);
            this.placeTypeEatingAdapter.setPlaceTypeModelCollection(eating);
            this.placeTypeShoppingAdapter.setPlaceTypeModelCollection(shopping);
        }
    }

    @Override
    public void requestSearch(PlaceRequestParams placeRequestParams) {
        if (this.searchParamsListener != null) {
            this.searchParamsListener.onGoBtnClicked(placeRequestParams);
        }
    }

    private void prepareViews() {
        setUpRecyclerViews();

        int defaultDistance = getResources().getInteger(R.integer.default_distance_param);
        et_distance.setText(Integer.toString(defaultDistance));
    }

    private void setUpRecyclerViews() {
        this.rv_amusement.setLayoutManager(new PlaceTypesLayoutManager(context()));
        this.rv_eating.setLayoutManager(new PlaceTypesLayoutManager(context()));
        this.rv_shopping.setLayoutManager(new PlaceTypesLayoutManager(context()));

        this.rv_amusement.setAdapter(placeTypeAmusementAdapter);
        this.rv_eating.setAdapter(placeTypeEatingAdapter);
        this.rv_shopping.setAdapter(placeTypeShoppingAdapter);
    }

    private List<String> getSelectedPlaceTypes() {
        List<String> selectedPlaceTypes = new ArrayList<>();

        selectedPlaceTypes.addAll(placeTypeAmusementAdapter.getSelectedItemsIds());
        selectedPlaceTypes.addAll(placeTypeEatingAdapter.getSelectedItemsIds());
        selectedPlaceTypes.addAll(placeTypeShoppingAdapter.getSelectedItemsIds());

        return selectedPlaceTypes;
    }

    private boolean getIsOpenAtTime() {
        return rg_open.getCheckedRadioButtonId() == R.id.spf_rb_open_at_time;
    }

    private int getMinimumRating() {
        return (int) r8_rating.getRating();
    }

    private int getMaximumPrice() {
        return (int) r8_price.getRating();
    }

    private String getDistanceText() {
        return et_distance.getText().toString();
    }

    private void loadPlaceTypes(){
        this.searchParamsPresenter.loadPlaceTypes();
    }

    @OnCheckedChanged(R.id.spf_rb_near_me) void onNearMeCheckedChanged() {
        if (rb_near_me.isSelected()) {
            rb_near_place.setText(getString(R.string.spf_rb_near_place_text));
        }
    }

    @OnClick(R.id.spf_rb_near_place) void onNearPlaceClicked() {
        if (searchParamsPresenter != null && searchParamsListener != null) {
            searchParamsListener.onRadButNearPlaceCheckedChanged();
        }
    }

    @OnClick(R.id.spf_rb_open_at_time) void onOpenAtTimeClicked() {
        if (rb_open_time.isChecked() && searchParamsPresenter != null) {
            new TimePickerDialog(context(), searchParamsPresenter, searchParamsPresenter.getOpenAtHour(),
                    searchParamsPresenter.getOpenAtMinute(), false)
                    .show();
        }
    }

    @OnClick(R.id.spf_btn_go) void onBtnGoClick() {
        if (searchParamsPresenter != null) {
            searchParamsPresenter.onGoBtnClicked();
        }
    }
}
