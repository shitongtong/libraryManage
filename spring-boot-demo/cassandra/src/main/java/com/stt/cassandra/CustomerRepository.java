package com.stt.cassandra;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
public interface CustomerRepository extends CrudRepository<Customer,String> {

    @Query("select from customer where firstname=?0")
    public Customer findByFirstName(String firstName);

    @Query("select from customer where lastname=?0")
    public List<Customer> findByLastName(String lastName);

}
