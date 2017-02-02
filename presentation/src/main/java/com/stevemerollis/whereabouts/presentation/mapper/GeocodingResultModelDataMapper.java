package com.stevemerollis.whereabouts.presentation.mapper;

import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.model.GeocodingResultModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class GeocodingResultModelDataMapper {

    @Inject
    public GeocodingResultModelDataMapper(){}

    public GeocodingResultModel transform(GeocodingResult geocodingResult) {
        if (geocodingResult == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        final GeocodingResultModel geocodingResultModel = new GeocodingResultModel();

        geocodingResultModel.formattedAddress = geocodingResult.formattedAddress;
        geocodingResultModel.lat = geocodingResult.lat;
        geocodingResultModel.lng = geocodingResult.lng;

        return geocodingResultModel;
    }

    public Collection<GeocodingResultModel> transform(Collection<GeocodingResult> geocodingResults) {
        Collection<GeocodingResultModel> geocodingResultModels;

        if (geocodingResults != null && !geocodingResults.isEmpty()) {
            geocodingResultModels = new ArrayList<>();

            for (GeocodingResult geocodingResult : geocodingResults) {
                geocodingResultModels.add(transform(geocodingResult));
            }
        } else {
            geocodingResultModels = Collections.emptyList();
        }

        return geocodingResultModels;
    }
}
