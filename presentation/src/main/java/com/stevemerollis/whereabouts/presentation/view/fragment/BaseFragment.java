package com.stevemerollis.whereabouts.presentation.view.fragment;

import android.app.Fragment;

import com.stevemerollis.whereabouts.presentation.di.HasComponent;

public class BaseFragment extends Fragment {

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
