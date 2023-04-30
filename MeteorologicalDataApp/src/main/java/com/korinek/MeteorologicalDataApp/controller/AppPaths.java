package com.korinek.MeteorologicalDataApp.controller;

public interface AppPaths {

    String CITY_PATH  = "/city";
    String CITY_ID_PATH = CITY_PATH + "/{id}";
    String CITIES_PATH  = "/cities";

    String STATE_PATH  = "/state";
    String STATE_ID_PATH = STATE_PATH + "/{id}";
    String STATES_PATH  = "/states";

    String MEASUREMENT_PATH  = "/measurement";
    String MEASUREMENT_ID_PATH = MEASUREMENT_PATH + "/{id}";
    String MEASUREMENTS_PATH  = "/measurements";
}
