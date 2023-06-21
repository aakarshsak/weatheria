package com.sinha.cityservice;

import java.util.List;

public interface CityService {

    public List<String> getCityNames();
    public List<City> getCities();
    public City addCity(City city);
}
