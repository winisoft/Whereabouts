package com.stevemerollis.whereabouts.presentation.view;

public interface SearchCoordinatesView extends LoadDataView {

    void showFoundImage(boolean show);
    void showNotFoundImage(boolean show);
    void setResults(String results);
}
