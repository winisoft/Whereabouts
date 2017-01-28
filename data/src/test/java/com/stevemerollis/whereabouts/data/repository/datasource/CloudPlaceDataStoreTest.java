package com.stevemerollis.whereabouts.data.repository.datasource;

import com.stevemerollis.whereabouts.data.net.place.PlaceRestApi;
import com.stevemerollis.whereabouts.data.repository.datasource.place.CloudPlaceDataStore;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudPlaceDataStoreTest {

    private CloudPlaceDataStore cloudPlaceDataStore;

    @Mock private PlaceRestApi mockPlaceRestApi;
    @Mock private PlaceRequestParams mockPlaceRequestParams;

    @Before
    public void setUp() {
        cloudPlaceDataStore = new CloudPlaceDataStore(mockPlaceRestApi);
    }

    /**
     * Confirm that when the {@link CloudPlaceDataStore} gets called for a list of place entities, it hits the RestApi's method to getPlaces PlaceEntities
     */
    @Test
    public void testGetPlaceEntityListFromApi() {
        cloudPlaceDataStore.getPlaceEntityList(mockPlaceRequestParams);
        verify(mockPlaceRestApi).getPlaceEntities(mockPlaceRequestParams);
    }
}
