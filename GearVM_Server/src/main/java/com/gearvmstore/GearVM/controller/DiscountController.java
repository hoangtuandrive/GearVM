package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @GetMapping(value = "/discount-code/{code}")
    public ResponseEntity<?> GetDiscountByCode(@PathVariable(value = "code")String code) {

        if(discountService.GetPercentDiscount(code) !=0){
            return ResponseEntity.ok().body(discountService.GetPercentDiscount(code));
        }
        return ResponseEntity.badRequest().body("Mã giảm giá đã hết hạn");
    }
}
