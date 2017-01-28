package com.stevemerollis.whereabouts.presentation.di.modules;

import android.content.Context;

import com.stevemerollis.whereabouts.data.executor.JobExecutor;
import com.stevemerollis.whereabouts.data.repository.PlaceDataRepository;
import com.stevemerollis.whereabouts.data.repository.PlaceTypeDataRepository;
import com.stevemerollis.whereabouts.domain.executor.PostExecutionThread;
import com.stevemerollis.whereabouts.domain.executor.ThreadExecutor;
import com.stevemerollis.whereabouts.domain.repository.PlaceRepository;
import com.stevemerollis.whereabouts.domain.repository.PlaceTypeRepository;
import com.stevemerollis.whereabouts.presentation.AndroidApplication;
import com.stevemerollis.whereabouts.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    PlaceRepository provideGooglePlaceRepository(PlaceDataRepository googlePlaceDataRepository){ return googlePlaceDataRepository; }

    @Provides @Singleton
    PlaceTypeRepository providePlaceTypeRepository(PlaceTypeDataRepository placeTypeDataRepository){ return placeTypeDataRepository; }
}