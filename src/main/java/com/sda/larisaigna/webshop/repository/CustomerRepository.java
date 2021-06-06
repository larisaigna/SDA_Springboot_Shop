package com.sda.larisaigna.webshop.repository;

import com.sda.larisaigna.webshop.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
