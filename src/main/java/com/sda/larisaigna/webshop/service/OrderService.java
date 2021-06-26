package com.sda.larisaigna.webshop.service;

import com.sda.larisaigna.webshop.model.*;
import com.sda.larisaigna.webshop.repository.BillRepository;
import com.sda.larisaigna.webshop.repository.CustomerRepository;
import com.sda.larisaigna.webshop.repository.OrderRepository;
import com.sda.larisaigna.webshop.repository.ProductRepository;
import com.sda.larisaigna.webshop.service.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BillRepository billRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, BillRepository billRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public void save(OrderDTO orderDTO) {
        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
        if(customer.isPresent()) {
            List<Product> products = new ArrayList<>();
            for (Long productId : orderDTO.getProductIds()) {
                Optional<Product> product = productRepository.findById(productId);
                if(product.isPresent()) {
                    products.add(product.get());
                }
                else {
                    throw new IllegalArgumentException("No product was found in data base for the given Id: " + productId);
                }
            }
            save(customer.get(), products);
        }
        else {
            throw new IllegalArgumentException("No customer was found in data base for the given Id: " + orderDTO.getCustomerId());
        }
    }

    public void save(Customer customer, List<Product> products) {
        Bill bill = new Bill();
        bill.setIssueDate(Timestamp.from(Instant.now()));
       // bill.setDueDate(Timestamp.from(Instant.now().plus(7, ChronoUnit.DAYS)));
        bill.setDueDate(Timestamp.from(Instant.now().plus(Duration.ofDays(7))));
        bill.setCurrency(products.get(0).getCurrency());
        double total = 0;
        for (Product product:products) {
            total = total + product.getPrice();
        }
        bill.setAmount(total);
        billRepository.save(bill);

        Order order = new Order();
        order.setProducts(products);
        order.setStatus(OrderStatus.NEW);
        order.setCreationDate(Timestamp.from(Instant.now()));
        order.setCustomer(customer);
        order.setBill(bill);
        orderRepository.save(order);
    }


    public void updateStatus(Long orderId, OrderStatus status) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            order.get().setStatus(status);
            orderRepository.save(order.get());
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
