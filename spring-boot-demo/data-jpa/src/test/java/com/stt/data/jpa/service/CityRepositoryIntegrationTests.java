package com.stt.data.jpa.service;

import com.stt.data.jpa.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryIntegrationTests {
    @Autowired
    CityRepository repository;

    @Test
    public void findsFirstPageOfCities() {

        PageRequest pageable = new PageRequest(0, 10);
        System.out.println(pageable);
        Page<City> cities = this.repository.findAll(pageable);
        System.out.println(pageable);
//        assertThat(cities.getTotalElements()).isGreaterThan(20L);
    }
}
