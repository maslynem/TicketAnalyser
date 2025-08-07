package com.example.TicketAnalyser.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum CityName {
    VLADIVOSTOK("Владивосток"),
    TEL_AVIV("Тель-Авив"),
    UFA("Уфа"),
    LARNACA("Ларнака");

    private final String cityName;

    CityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonCreator
    public static CityName fromString(String value) {
        for (CityName city : CityName.values()) {
            if (city.cityName.equalsIgnoreCase(value)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Unknown city name: " + value);
    }
}
