package com.fetch.rewards.components.schemas;

public record Receipt(String retailer, String purchaseDate, String purchaseTime, String total, Item[] items) {}
