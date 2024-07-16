package com.fetch.rewards.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.rewards.components.schemas.Receipt;
import com.fetch.rewards.repository.RequestBodyValidator;
import com.fetch.rewards.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessController {
    private final ReceiptService receiptService;
    private final RequestBodyValidator requestBodyValidator;

    public ReceiptProcessController(ReceiptService receiptService, RequestBodyValidator requestBodyValidator) {
        this.receiptService = receiptService;
        this.requestBodyValidator = requestBodyValidator;
    }

    /*
     POST endpoint process receipts /receipts/process
     GET endpoint get points /receipts/{id}/points
     */

    @PostMapping(value="/process")
    public ResponseEntity<Object> processReceipts(@RequestBody String requestBody) {

        if(requestBodyValidator.isValidJson(requestBody, Receipt.class)) {
            Receipt receipt;
            try {
                receipt = new ObjectMapper().readValue(requestBody, Receipt.class);
                String id = receiptService.processReceipt(receipt);
                Map<String,String> response = new HashMap<>();
                response.put("id", id);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The receipt is invalid");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The receipt is invalid");
    }

    @GetMapping(value="/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable ("id") String id) {
        Integer points = receiptService.getPoints(id);
        if(points == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No receipt found for that id");
        }
        Map<String,Integer> response = new HashMap<>();
        response.put("points", points);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
