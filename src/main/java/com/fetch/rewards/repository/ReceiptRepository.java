package com.fetch.rewards.repository;

import com.fetch.rewards.components.schemas.Receipt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ReceiptRepository {
    private final Map<String, Receipt> receipts = new HashMap<>();

    public String saveReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }

    public Receipt getReceiptById(String id) {
        return receipts.get(id);
    }
}
