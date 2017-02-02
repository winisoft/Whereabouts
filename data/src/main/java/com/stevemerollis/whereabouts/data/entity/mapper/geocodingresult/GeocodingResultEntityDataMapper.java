package com.stevemerollis.whereabouts.data.entity.mapper.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;
import com.stevemerollis.whereabouts.domain.GeocodingResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class GeocodingResultEntityDataMapper {

    @Inject
    GeocodingResultEntityDataMapper(){}

    public GeocodingResult transform(GeocodingResultEntity geocodingResultEntity) {
        GeocodingResult geocodingResult = null;

        if (geocodingResultEntity != null) {
            geocodingResult = new GeocodingResult();
            geocodingResult.formattedAddress = geocodingResultEntity.formattedAddress;
            geocodingResult.lat = geocodingResultEntity.geometry.location.lat;
            geocodingResult.lng = geocodingResultEntity.geometry.location.lng;
        }

        return geocodingResult;
    }

    public List<GeocodingResult> transform(Collection<GeocodingResultEntity> geocodingResultEntityCollection) {
        List<GeocodingResult> geocodingResults = new ArrayList<>();

        for (GeocodingResultEntity geocodingResultEntity : geocodingResultEntityCollection) {
            final GeocodingResult geocodingResult = transform(geocodingResultEntity);

            if (geocodingResult != null) {
                geocodingResults.add(geocodingResult);
            }
        }

        return geocodingResults;
    }
}
