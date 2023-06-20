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

    private CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<City>> getCities() {
        return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        cityRepository.save(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }
}
