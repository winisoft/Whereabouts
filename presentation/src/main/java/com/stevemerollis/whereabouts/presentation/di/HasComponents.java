package com.stevemerollis.whereabouts.presentation.di;

public interface HasComponents<C1, C2> {

    C1 getComponentA();
    C2 getComponentB();
}
