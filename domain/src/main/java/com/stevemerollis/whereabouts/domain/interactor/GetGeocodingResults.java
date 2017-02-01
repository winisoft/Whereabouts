package com.stevemerollis.whereabouts.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.GeocodingResultRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetGeocodingResults extends UseCase<List<GeocodingResult>, GetGeocodingResults.Params> {

    private GeocodingResultRepository geocodingResultRepository;

    @Inject
    GetGeocodingResults(GeocodingResultRepository geocodingResultRepository, ThreadExecutor threadExecutor,
              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.geocodingResultRepository = geocodingResultRepository;
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
