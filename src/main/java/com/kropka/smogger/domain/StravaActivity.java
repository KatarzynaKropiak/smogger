package com.kropka.smogger.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StravaActivity {

    @JsonProperty("name")
    String name;
    @JsonProperty("distance")
    float distance;
    @JsonProperty("id")
    long id;
    @JsonProperty("end_latlng")
    float[] end_latlng;
}
