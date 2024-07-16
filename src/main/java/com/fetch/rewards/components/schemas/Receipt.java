package com.fetch.rewards.components.schemas;

public record Receipt(String description,String purchaseDate,
        String purchaseTime,
        String total,
        Item[] items) {}
