package com.stt.data.jpa.service;

import com.stt.data.jpa.domain.City;
import com.stt.data.jpa.domain.HotelSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
public interface CityService {
    Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);

    City getCity(String name, String country);

    Page<HotelSummary> getHotels(City city, Pageable pageable);

    Page<City> findAll(Pageable pageable);
}
