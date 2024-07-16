package com.fetch.rewards.service;


import com.fetch.rewards.components.schemas.Item;
import com.fetch.rewards.components.schemas.Receipt;
import com.fetch.rewards.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(Receipt receipt) {
        return receiptRepository.saveReceipt(receipt);
    }

    public int calculatePoints(String id) {
        Receipt receipt = receiptRepository.getReceiptById(id);
        if(receipt == null) {
            // no receipt with the provided id
            return 0;
        }

        int points = 0;
        // Rule 1: One point for every alphanumeric character in the retailer name
        points += countAlphanumericCharacters(receipt.retailer());

        // Rule 2: 50 points if the total is a round dollar amount with no cents
        if (Double.parseDouble(receipt.total()) % 1 == 0) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25
        if (Double.parseDouble(receipt.total()) % 0.25 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items on the receipt
        points += (receipt.items().length / 2) * 5;

        // Rule 5: Points based on item description length
        for (Item item : receipt.items()) {
            if (item.shortDescription().trim().length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble( item.price()) * 0.2);
            }
        }

        // Rule 6: 6 points if the day in the purchase date is odd
        if (Integer.parseInt(receipt.purchaseDate().split("-")[2]) % 2 != 0) {
            points += 6;
        }

        // Rule 7: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        String[] timeParts = receipt.purchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 14 && hour < 16) {
            points += 10;
        }

        return points;
    }

    private int countAlphanumericCharacters(String str) {
        return (int) Pattern.compile("[a-zA-Z0-9]").matcher(str).results().count();
    }
}
