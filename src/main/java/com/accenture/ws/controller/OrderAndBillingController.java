package com.accenture.ws.controller;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import com.accenture.ws.impl.DiscountedBill;
import com.accenture.ws.impl.OrderBill;
import com.accenture.ws.impl.RegularBill;
import com.accenture.ws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/orders")
    public List<Order> getOrderList() {
        return orderRepo.findAll();
    }


    @PostMapping("/add-order")
    public void addOrder(@RequestBody Order order) {
        orderRepo.save(order);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrderOpt = orderRepo.findById(id);

        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            // Update only the fields that are passed from the frontend
            existingOrder.setOrderName(updatedOrder.getOrderName());
            existingOrder.setPrice(updatedOrder.getPrice());
            existingOrder.setDiscounted(updatedOrder.isDiscounted()); // Preserve the isDiscounted flag

            // Save the updated order
            orderRepo.save(existingOrder);
            return ResponseEntity.ok("Order updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
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
