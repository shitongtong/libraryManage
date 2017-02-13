package com.stt.cache;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
public class Country implements Serializable {

    private final String code;

    public Country(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return code != null ? code.equals(country.code) : country.code == null;

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
