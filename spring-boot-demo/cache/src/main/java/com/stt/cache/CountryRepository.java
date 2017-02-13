package com.stt.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@Component
@CacheConfig(cacheNames = "countries")
public class CountryRepository {

    @Cacheable
    public Country findByCode(String code){
        System.out.println("---> Loading country with code '" + code + "'");
        return new Country(code);
    }

}
