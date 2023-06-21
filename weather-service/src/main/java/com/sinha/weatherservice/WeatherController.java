package com.sinha.weatherservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Value("${weather.api}")
    private String apiKey;

    @GetMapping("")
    public String sayApi() {
        return apiKey;
    }

}
