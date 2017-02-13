package com.stt.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@SpringBootApplication
public class SampleCassandraApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... strings) throws Exception {
        this.customerRepository.deleteAll();

        // save a couple of customers
        this.customerRepository.save(new Customer(UUIDs.timeBased(),"Alice","Smith"));
        this.customerRepository.save(new Customer(UUIDs.timeBased(),"Bob","Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : this.customerRepository.findAll()){
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(this.customerRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : this.customerRepository.findByLastName("Smith")){
            System.out.println(customer);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleCassandraApplication.class,args);
    }
}
