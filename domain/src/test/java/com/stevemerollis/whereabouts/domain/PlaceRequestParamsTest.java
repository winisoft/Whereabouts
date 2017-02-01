package com.stevemerollis.whereabouts.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceRequestParamsTest {

    private static final double FAKE_LATITUDE = 30.1;
    private static final double FAKE_LONGITUDE = 112.0;
    private static final int FAKE_PROXIMITY_RADIUS = 10000;
    private static final List<String> FAKE_PLACE_TYPES = new ArrayList<String>();

    private PlaceRequestParams params;

    @Before
    public void setUp() {
        params = new PlaceRequestParams();
        params.latitude = FAKE_LATITUDE;
        params.longitude = FAKE_LONGITUDE;
        params.proximityRadius = FAKE_PROXIMITY_RADIUS;
        params.placeTypes = FAKE_PLACE_TYPES;
    }

    @Test
    public void testPlaceRequestParamsConstructor() {
        assertThat(params.getLatitude()).isEqualTo(FAKE_LATITUDE);
        assertThat(params.getLongitude()).isEqualTo(FAKE_LONGITUDE);
        assertThat(params.getProximityRadius()).isEqualTo(FAKE_PROXIMITY_RADIUS);
        assertThat(params.getPlaceTypes()).isEqualTo(FAKE_PLACE_TYPES);
    }
}
