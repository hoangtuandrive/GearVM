package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.dto.purchase.CreatePurchase;
import com.gearvmstore.GearVM.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<?> getAllPurchases() {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getPurchases());
    }

    @GetMapping("get-all-filter")
    public ResponseEntity<?> getAllPurchasesByFilter(@RequestParam(defaultValue = "") String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.getPurchasesByFilter(filter, filter, filter, filter, filter));
    }


    @PostMapping()
    public ResponseEntity<?> createPurchase(@RequestBody CreatePurchase createPurchase) {
        return ResponseEntity.status(HttpStatus.OK).body(purchaseService.createPurchase(createPurchase));
    }

}
