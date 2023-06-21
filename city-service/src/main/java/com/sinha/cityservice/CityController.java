package com.sinha.cityservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    Logger logger = LoggerFactory.getLogger(CityController.class);

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("")
    public ResponseEntity<List<City>> getCities() {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getCitiesNames() {
        return new ResponseEntity<>(cityService.getCityNames(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        cityService.addCity(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }
}
