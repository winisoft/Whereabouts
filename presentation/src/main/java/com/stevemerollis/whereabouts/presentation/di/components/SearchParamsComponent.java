package com.stevemerollis.whereabouts.presentation.di.components;


import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.di.modules.ActivityModule;
import com.stevemerollis.whereabouts.presentation.di.modules.SearchParamsModule;
import com.stevemerollis.whereabouts.presentation.view.fragment.SearchParamsFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fragments used as part of the places functionality.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, SearchParamsModule.class})
public interface SearchParamsComponent extends ActivityComponent {

    void inject(SearchParamsFragment searchParamsFragment);
}