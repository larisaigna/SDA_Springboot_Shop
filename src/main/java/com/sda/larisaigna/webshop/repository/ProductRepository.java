package com.sda.larisaigna.webshop.repository;

import com.sda.larisaigna.webshop.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <Product, Long> {
}
