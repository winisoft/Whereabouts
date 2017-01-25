package com.stevemerollis.whereabouts.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceRequestParamsTest {

    private static final double FAKE_LATITUDE = 30.1;
    private static final double FAKE_LONGITUDE = 112.0;
    private static final int FAKE_PROXIMITY_RADIUS = 10000;
    private static final String FAKE_ESTABLISHMENT_TYPE = "restaurant";

    private PlaceRequestParams params;

    @Before
    public void setUp() { params = new PlaceRequestParams(FAKE_LATITUDE, FAKE_LONGITUDE, FAKE_PROXIMITY_RADIUS, FAKE_ESTABLISHMENT_TYPE); }

    @Test
    public void testPlaceRequestParamsConstructor() {
        assertThat(params.getLatitude()).isEqualTo(FAKE_LATITUDE);
        assertThat(params.getLongitude()).isEqualTo(FAKE_LONGITUDE);
        assertThat(params.getProximityRadius()).isEqualTo(FAKE_PROXIMITY_RADIUS);
        assertThat(params.getEstablishmentType()).isEqualTo(FAKE_ESTABLISHMENT_TYPE);
    }
}
