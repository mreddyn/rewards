package com.fetch.rewards.service;

import com.fetch.rewards.components.schemas.Receipt;
import com.fetch.rewards.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(Receipt receipt) {
        return receiptRepository.saveReceipt(receipt);
    }

    public Integer getPoints(String id) {
        return receiptRepository.getPointsById(id);
    }
}
