package com.stevemerollis.whereabouts.presentation.di.components;

import android.content.Context;

import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.PlaceRepository;
import com.stevemerollis.whereabouts.domain.repository.PlaceTypeRepository;
import com.stevemerollis.whereabouts.presentation.di.modules.ApplicationModule;
import com.stevemerollis.whereabouts.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton //one-per-application
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    PlaceRepository placeRepository();
    PlaceTypeRepository placeTypeRepository();
}
