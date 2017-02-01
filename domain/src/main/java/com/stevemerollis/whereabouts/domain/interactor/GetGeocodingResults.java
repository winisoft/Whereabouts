package com.stevemerollis.whereabouts.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.PlaceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetGeocodingResults extends UseCase<List<GeocodingResult>, GetGeocodingResults.Params> {

    @Inject
    GetGeocodingResults(PlaceRepository placeRepository, ThreadExecutor threadExecutor,
              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        //this.placeRepository = placeRepository;
    }

    @Override
    Observable<List<GeocodingResult>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return null;
    }

    public static final class Params {

        private final String input;

        private Params(String input) {
            this.input = input;
        }

        public static Params forQuery(String input) {
            return new Params(input);
        }
    }
}
