package com.fetch.rewards.controllers;

import com.fetch.rewards.components.schemas.Receipt;
import com.fetch.rewards.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessController {
    private final ReceiptService receiptService;

    public ReceiptProcessController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    /*
     POST endpoint process receipts /receipts/process
     GET endpoint get points /receipts/{id}/points
     */

    @PostMapping(value="/process")
    public ResponseEntity<String> processReceipts(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok(id);
    }

    @GetMapping(value="/{id}/points")
    public ResponseEntity<Integer> getPoints(@PathVariable ("id") String id) {
        int points = receiptService.calculatePoints(id);

        return ResponseEntity.ok(points);

    }
}
