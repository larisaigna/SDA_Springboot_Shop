package com.sda.larisaigna.webshop.repository;

import com.sda.larisaigna.webshop.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
