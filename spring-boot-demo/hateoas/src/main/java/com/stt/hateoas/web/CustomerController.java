package com.stt.hateoas.web;

import com.stt.hateoas.domain.Customer;
import com.stt.hateoas.domain.CustomerRepository;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017-02-13.
 *
 * @author shitongtong
 */
@Controller
@RequestMapping("customers")
//声明其所暴露的模型类
@ExposesResourceFor(Customer.class)
public class CustomerController {

    private final CustomerRepository repository;

    private final EntityLinks entityLinks;

    public CustomerController(CustomerRepository repository,EntityLinks entityLinks){
        this.repository = repository;
        this.entityLinks = entityLinks;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resources<Customer>> showCustomers(){
        Resources<Customer> resource = new Resources<>(this.repository.findAll());
        resource.add(this.entityLinks.linkToCollectionResource(Customer.class));
        return new ResponseEntity<Resources<Customer>>(resource, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Customer>> showCustomer(@PathVariable Long id){
        Resource<Customer> resource = new Resource<>(this.repository.findOne(id));
        resource.add(this.entityLinks.linkToSingleResource(Customer.class,id));
        return new ResponseEntity<Resource<Customer>>(resource,HttpStatus.OK);
    }

}
