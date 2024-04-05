package com.kropka.smogger.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class AQIndex {
    int stationId;
    DateTimeFormat stCalcDate;
    String stIndexLevel;
}
