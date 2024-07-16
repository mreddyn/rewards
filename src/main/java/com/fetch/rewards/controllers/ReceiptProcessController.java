package com.fetch.rewards.controllers;

import com.fetch.rewards.components.schemas.Receipt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReceiptProcessController {

    /*
     POST endpoint process receipts /receipts/process
     GET endpoint get points /receipts/{id}/points
     */

    @PostMapping(value="/receipts/process")
    public ResponseEntity<String> processReceipts(@RequestBody Receipt receipt) {

    }

    @GetMapping(value="/receipts/{id}/points")
    public ResponseEntity<Integer> getPoints(@PathVariable ("id") String id) {

    }
}
