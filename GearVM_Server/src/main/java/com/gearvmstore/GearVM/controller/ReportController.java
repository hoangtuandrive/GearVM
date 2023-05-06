package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("most-sold-products")
    public ResponseEntity<?> getMostSoldProducts() {
        return new ResponseEntity<>(reportService.getMostSoldItems(), HttpStatus.OK);
    }
}
