package com.accenture.ws.impl;

import com.accenture.ws.entity.CafeClerk;

public class DiscountedBill extends OrderBill {

    public DiscountedBill(CafeClerk clerk) {
        super(clerk);
    }

    @Override
    public double getTotalBill() {
        return getOrderList().stream()
                .mapToDouble(order -> {
                    if (order.isDiscounted()) {
                        return order.getPrice() - (order.getPrice() * order.getDiscountedPercentage() / 100);
                    }
                    return order.getPrice();
                })
                .sum();
    }
}