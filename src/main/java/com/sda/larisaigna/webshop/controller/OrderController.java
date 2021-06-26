package com.sda.larisaigna.webshop.controller;

import com.sda.larisaigna.webshop.model.Order;
import com.sda.larisaigna.webshop.model.OrderStatus;
import com.sda.larisaigna.webshop.service.OrderService;
import com.sda.larisaigna.webshop.service.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/orders")
    public String addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
        return "Order created";
    }

    @PatchMapping("/orders/{id}")
    public String changeStatus(@PathVariable(value = "id") Long orderId, @RequestBody String status) {
        orderService.updateStatus(orderId, OrderStatus.valueOf(status));
        return "Status changed";
    }
}
