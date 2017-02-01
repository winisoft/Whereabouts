package com.stevemerollis.whereabouts.domain.repository;

import com.stevemerollis.whereabouts.domain.GeocodingResult;

import java.util.List;

import io.reactivex.Observable;

public interface GeocodingResultRepository {

    Observable<List<GeocodingResult>> geocodingResults(final String userQuery);
}
