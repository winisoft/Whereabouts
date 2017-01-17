package com.stevemerollis.whereabouts.presentation.view.fragment;

import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;

public abstract class BaseMapFragment extends MapFragment {

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
