package com.stevemerollis.whereabouts.data.repository.datasource;

import com.stevemerollis.whereabouts.data.net.RestApi;
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

    @Mock private RestApi mockRestApi;
    @Mock private PlaceRequestParams mockPlaceRequestParams;

    @Before
    public void setUp() {
        cloudPlaceDataStore = new CloudPlaceDataStore(mockRestApi);
    }

    /**
     * Confirm that when the {@link CloudPlaceDataStore} gets called for a list of place entities, it hits the RestApi's method to get PlaceEntities
     */
    @Test
    public void testGetPlaceEntityListFromApi() {
        cloudPlaceDataStore.getPlaceEntityList(mockPlaceRequestParams);
        verify(mockRestApi).getPlaceEntities(mockPlaceRequestParams);
    }
}
