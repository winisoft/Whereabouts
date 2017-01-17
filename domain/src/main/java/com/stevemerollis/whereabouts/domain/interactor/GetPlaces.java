package com.stevemerollis.whereabouts.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import com.fernandocejas.arrow.checks.Preconditions;
import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.PlaceRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Place}.
 */
public class GetPlaces extends UseCase<List<Place>, GetPlaces.Params> {

    private final PlaceRepository placeRepository;

    @Inject
    GetPlaces(PlaceRepository placeRepository, ThreadExecutor threadExecutor,
              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.placeRepository = placeRepository;
    }

    @Override Observable<List<Place>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.placeRepository.places(params.placeRequestParams);
    }

    public static final class Params {

        private PlaceRequestParams placeRequestParams;

        private Params(PlaceRequestParams params) {
            this.placeRequestParams = params;
        }

        public static Params forPlaces(PlaceRequestParams params) {
            return new Params(params);
        }
    }
}
