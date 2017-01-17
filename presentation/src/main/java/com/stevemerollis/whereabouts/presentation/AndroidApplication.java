package com.stevemerollis.whereabouts.presentation;

import android.app.Application;

import com.stevemerollis.whereabouts.presentation.di.components.ApplicationComponent;
import com.stevemerollis.whereabouts.presentation.di.components.DaggerApplicationComponent;
import com.stevemerollis.whereabouts.presentation.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
