package com.stt.data.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
public interface CustomerRepository extends MongoRepository<Customer,String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
