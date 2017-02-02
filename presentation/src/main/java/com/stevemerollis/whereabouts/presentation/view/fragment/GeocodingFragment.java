package com.stevemerollis.whereabouts.presentation.view.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;
import com.stevemerollis.whereabouts.presentation.di.components.SearchParamsComponent;
import com.stevemerollis.whereabouts.presentation.model.GeocodingResultModel;
import com.stevemerollis.whereabouts.presentation.presenter.GeocodingPresenter;
import com.stevemerollis.whereabouts.presentation.view.GeocodingView;
import com.stevemerollis.whereabouts.presentation.view.adapter.GeocodingResultAdapter;
import com.stevemerollis.whereabouts.presentation.view.adapter.GeocodingResultLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeocodingFragment extends DialogFragment implements GeocodingView {

    public interface GeocodingListener {

    }

    @Inject GeocodingPresenter geocodingPresenter;
    @Inject GeocodingResultAdapter geocodingResultAdapter;
    @Bind(R.id.gcf_et_vicinity) EditText et_vicinity;
    @Bind(R.id.gcf_rv_results) RecyclerView rv_results;
    @Bind(R.id.gcf_btn_find) Button btn_find;
    @Bind(R.id.gcf_btn_ok) Button btn_ok;
    @Bind(R.id.gcf_btn_cancel) Button btn_cancel;

    private GeocodingListener geocodingListener;

    public GeocodingFragment() { setRetainInstance(true); }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof GeocodingListener) {
            this.geocodingListener = (GeocodingListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(SearchParamsComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_geocoding, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.geocodingPresenter.setView(this);
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

    @Override public Context context() { return getActivity(); }

    @Override
    public void renderGeocodingResults(Collection<GeocodingResultModel> geocodingResultModels) {
        geocodingResultAdapter.setGeocodingResultModelCollection(geocodingResultModels);
    }

    private void setUpRecyclerView() {
        this.rv_results.setLayoutManager(new GeocodingResultLayoutManager(context()));
        this.rv_results.setAdapter(geocodingResultAdapter);
    }

    private void geocode(String input) {
        this.geocodingPresenter.geocode(input);
    }

    @OnClick(R.id.gcf_btn_find) void onFindBtnClick() {
        geocode(et_vicinity.getText().toString());
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
