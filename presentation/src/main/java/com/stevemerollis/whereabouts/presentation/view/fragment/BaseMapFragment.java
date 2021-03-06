package com.stevemerollis.whereabouts.presentation.view.fragment;

import com.google.android.gms.maps.MapFragment;
import com.stevemerollis.whereabouts.presentation.di.HasComponent;

public class BaseMapFragment extends MapFragment {

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
