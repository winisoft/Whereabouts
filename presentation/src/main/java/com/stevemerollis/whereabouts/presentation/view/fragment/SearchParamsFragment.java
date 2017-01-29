package com.stevemerollis.whereabouts.presentation.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.view.adapter.PlaceTypeModelAdapter;
import com.stevemerollis.whereabouts.presentation.di.components.SearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;
import com.stevemerollis.whereabouts.presentation.presenter.SearchParamsPresenter;
import com.stevemerollis.whereabouts.presentation.view.SearchParamsView;
import com.stevemerollis.whereabouts.presentation.view.adapter.PlaceTypesLayoutManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchParamsFragment extends BaseFragment implements SearchParamsView {

    public interface SearchParamsListener {
        void onGoBtnClicked(final PlaceRequestParams placeRequestParams);
    }

    @Inject SearchParamsPresenter searchParamsPresenter;
    @Inject PlaceTypeModelAdapter placeTypeAmusementAdapter;
    @Inject PlaceTypeModelAdapter placeTypeEatingAdapter;
    @Inject PlaceTypeModelAdapter placeTypeShoppingAdapter;

    @Bind(R.id.spf_rv_amusement) RecyclerView rv_amusement;
    @Bind(R.id.spf_rv_eating) RecyclerView rv_eating;
    @Bind(R.id.spf_rv_shopping) RecyclerView rv_shopping;
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

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_search_params, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpRecyclerViews();
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

    private void setUpRecyclerViews() {
        this.rv_amusement.setLayoutManager(new PlaceTypesLayoutManager(context()));
        this.rv_eating.setLayoutManager(new PlaceTypesLayoutManager(context()));
        this.rv_shopping.setLayoutManager(new PlaceTypesLayoutManager(context()));

        this.rv_amusement.setAdapter(placeTypeAmusementAdapter);
        this.rv_eating.setAdapter(placeTypeEatingAdapter);
        this.rv_shopping.setAdapter(placeTypeShoppingAdapter);
    }

    private void loadPlaceTypes(){
        this.searchParamsPresenter.loadPlaceTypes();
    }

    @OnClick(R.id.spf_btn_go) void onBtnGoClick() {
        if (searchParamsPresenter != null) {
            searchParamsPresenter.onGoBtnClicked();
        }
    }
}
