package com.sinha.cityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService{

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<String> getCityNames() {

        List<City> citiesList = getCities();
        List<String> cities = citiesList.stream().map(City::getCityName).collect(Collectors.toList());
        return cities;
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll();
    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }
}
