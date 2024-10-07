package com.accenture.ws.controller;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import com.accenture.ws.impl.DiscountedBill;
import com.accenture.ws.impl.OrderBill;
import com.accenture.ws.impl.RegularBill;
import com.accenture.ws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order-billing-ws")
public class OrderAndBillingController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private final CafeClerk clerk;

    public OrderAndBillingController(CafeClerk clerk) {
        this.clerk = clerk;
    }

    @GetMapping
    public List<Order> getOrderList() {
        return orderRepo.findAll();
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderRepo.save(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderRepo.existsById(id)) {
            order.setId(id);
            orderRepo.save(order);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
    }

    @GetMapping("/totalRegularBill")
    public OrderBill getTotalRegularBill() {
        OrderBill regularBill = new RegularBill(clerk);
        regularBill.setOrderList(orderRepo.findAll());
        return regularBill;
    }

    @GetMapping("/totalDiscountedBill")
    public OrderBill getTotalDiscountedBill() {
        OrderBill discountedBill = new DiscountedBill(clerk);
        discountedBill.setOrderList(orderRepo.findAll());
        return discountedBill;
    }
}
