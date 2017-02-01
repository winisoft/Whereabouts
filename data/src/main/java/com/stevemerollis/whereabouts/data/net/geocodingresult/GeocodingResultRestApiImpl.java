package com.stevemerollis.whereabouts.data.net.geocodingresult;

import android.content.Context;

import com.stevemerollis.whereabouts.data.entity.GeocodingResultEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.geocodingresult.GeocodingResultEntityJsonMapper;
import com.stevemerollis.whereabouts.data.exception.NetworkConnectionException;
import com.stevemerollis.whereabouts.data.mock.MockResponse;
import com.stevemerollis.whereabouts.data.net.BaseApiImpl;

import java.util.List;

import io.reactivex.Observable;

public class GeocodingResultRestApiImpl extends BaseApiImpl implements GeocodingResultRestApi {

    private final GeocodingResultEntityJsonMapper geocodingResultEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param geocodingResultEntityJsonMapper {@link GeocodingResultEntityJsonMapper}.
     */
    public GeocodingResultRestApiImpl(Context context, GeocodingResultEntityJsonMapper geocodingResultEntityJsonMapper) {
        super(context);

        if (geocodingResultEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }

        this.geocodingResultEntityJsonMapper = geocodingResultEntityJsonMapper;
    }


    @Override
    public Observable<List<GeocodingResultEntity>> getPlaceEntities(String userQuery) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String apiResponse = MockResponse.getPlaces().replace('"', '\"');
                    if (apiResponse != null) {
                        List<GeocodingResultEntity> placeEntities = geocodingResultEntityJsonMapper.transform(apiResponse);
                        emitter.onNext(placeEntities);
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }
}
