package com.stt.data.mongodb;

import org.springframework.data.annotation.Id;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Customer(){

    }

    public Customer(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id,
                firstName, lastName);
    }
}
