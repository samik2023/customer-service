package com.inventory.management.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.management.demo.entity.Customer;
import com.inventory.management.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping(value = "/customers/{cid}",produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerById(@PathVariable Long cid) {
        Optional<Customer> customer = repository.findById(Long.valueOf(cid));
        if (!customer.isEmpty()) {
            log.info("Customer found with customer id : {}", cid);
            ObjectMapper mapper = new ObjectMapper();
            try {
                return ResponseEntity.ok().body(mapper.writeValueAsString(customer.get()));
            } catch (JsonProcessingException e) {
                return new ResponseEntity<>("customer id not found", HttpStatus.NOT_FOUND);
            }
        } else {
            log.info("no customer found for customer id : {}", cid);
            return new ResponseEntity<>("customer id not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/customers" ,produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = repository.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping(value = "/customers" ,
            produces =APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        repository.save(customer);
        log.info("New Customer saved with customer id :{}", customer.getId());
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping(value = "/customers/{cid}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String cid){
        repository.deleteById(Long.valueOf(cid));
        log.info("Customer deleted with customer id :{}", cid);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}