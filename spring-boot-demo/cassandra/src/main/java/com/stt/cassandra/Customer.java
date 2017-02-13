package com.stt.cassandra;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@Table
public class Customer {

    @PrimaryKey
    private UUID id;

    private String firstName;

    private String lastName;

    public Customer(){

    }

    public Customer(UUID id,String firstName,String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s']", this.id,
                this.firstName, this.lastName);
    }
}
