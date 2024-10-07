package com.accenture.ws.entity;


import org.springframework.stereotype.Component;

@Component
public class CafeClerk {
    private String name;

    public CafeClerk() {
        this.name = "Ja and Dj Store";  // You can initialize this with a default value
    }

    public CafeClerk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
