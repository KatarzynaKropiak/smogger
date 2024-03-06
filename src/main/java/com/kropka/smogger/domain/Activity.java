package com.kropka.smogger.domain;

import lombok.Data;

@Data
public class Activity {

    String name;
    float distance;
    long id;
    float[] end_latlng;
}
