package com.stevemerollis.whereabouts.presentation.view;

import com.stevemerollis.whereabouts.presentation.model.GeocodingResultModel;

import java.util.Collection;

public interface GeocodingView extends LoadDataView {

    void renderGeocodingResults(Collection<GeocodingResultModel> geocodingResultModels);
}
