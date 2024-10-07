package com.accenture.ws.impl;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;

public class RegularBill extends OrderBill {

    public RegularBill(CafeClerk clerk) {
        super(clerk);
    }

    @Override
    public double getTotalBill() {
        return getOrderList().stream()
                .mapToDouble(Order::getPrice)
                .sum();
    }
}