package com.stevemerollis.whereabouts.data.net.placetype;

import android.content.Context;

import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.placetype.PlaceTypeEntityJsonMapper;
import com.stevemerollis.whereabouts.data.exception.NetworkConnectionException;
import com.stevemerollis.whereabouts.data.mock.MockResponse;
import com.stevemerollis.whereabouts.data.net.BaseApiImpl;

import java.util.List;

import io.reactivex.Observable;

public class PlaceTypeRestApiImpl extends BaseApiImpl implements PlaceTypeRestApi {

    private final PlaceTypeEntityJsonMapper placeTypeEntityJsonMapper;

    public PlaceTypeRestApiImpl(Context context, PlaceTypeEntityJsonMapper placeTypeEntityJsonMapper) {
        super(context);

        if (placeTypeEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }

        this.placeTypeEntityJsonMapper = placeTypeEntityJsonMapper;
    }

    @Override
    public Observable<List<PlaceTypeEntity>> getPlaceTypeEntities() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String apiResponse = MockResponse.getPlaceTypes().replace('"', '\"');
                    if (apiResponse != null) {
                        List<PlaceTypeEntity> placeTypeEntities = placeTypeEntityJsonMapper.transform(apiResponse);
                        emitter.onNext(placeTypeEntities);
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
