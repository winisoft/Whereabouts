package com.stevemerollis.whereabouts.domain.interactor;

import com.stevemerollis.whereabouts.domain.PlaceType;
import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.PlaceTypeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPlaceTypes extends UseCase<List<PlaceType>, Void> {

    private PlaceTypeRepository placeTypeRepository;

    @Inject
    GetPlaceTypes(PlaceTypeRepository placeTypeRepository, ThreadExecutor threadExecutor,
                  PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.placeTypeRepository = placeTypeRepository;
    }

    @Override
    Observable<List<PlaceType>> buildUseCaseObservable(Void noParams) {
        return this.placeTypeRepository.placeTypes();
    }
}
