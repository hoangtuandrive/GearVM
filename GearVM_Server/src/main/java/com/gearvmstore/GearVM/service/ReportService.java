package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.response.MonthlyFinanceReportResponseModel;
import com.gearvmstore.GearVM.model.response.MostSoldProductResponseModel;
import com.gearvmstore.GearVM.repository.OrderItemRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import com.gearvmstore.GearVM.repository.ProductRepository;
import com.gearvmstore.GearVM.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private final ModelMapper modelMapper;
    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public ReportService(ModelMapper modelMapper, PurchaseRepository purchaseRepository, ProductService productService, ProductRepository productRepository, OrderService orderService, OrderRepository orderRepository,
                         OrderItemRepository orderItemRepository) {
        this.modelMapper = modelMapper;
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<MostSoldProductResponseModel> getMostSoldItems() {
        Pageable pageable = PageRequest.of(0, 10);
        return orderItemRepository.findMostSoldItems(pageable);
    }

    public List<MonthlyFinanceReportResponseModel> getMonthlyFinanceReports(int year) {
        List<MonthlyFinanceReportResponseModel> monthlyFinanceReport = new ArrayList<>();

        List<MonthlyFinanceReportResponseModel> monthlyEarnings = orderRepository.calculateMonthlyRevenue(year);
        List<MonthlyFinanceReportResponseModel> monthlyCost = purchaseRepository.calculateMonthlyCost(year);

        // Combine the results
        for (int i = 0; i < 12; i++) {
            MonthlyFinanceReportResponseModel report = new MonthlyFinanceReportResponseModel();
            report.setMonth(i + 1);

            double revenue = 0.0;
            double cost = 0.0;

            // Find the matching earnings and cost for the month
            for (MonthlyFinanceReportResponseModel earning : monthlyEarnings) {
                if (earning.getMonth() == i + 1) {
                    revenue = earning.getRevenue();
                    break;
                }
            }

            for (MonthlyFinanceReportResponseModel spent : monthlyCost) {
                if (spent.getMonth() == i + 1) {
                    cost = spent.getCost();
                    break;
                }
            }

            // Calculate profit and loss
            double profit = revenue - cost;
            double loss = 0.0;
            if (profit < 0) {
                loss = Math.abs(profit);
                profit = 0.0;
            }

            report.setRevenue(revenue);
            report.setCost(cost);
            report.setProfit(profit);
            report.setLoss(loss);

            monthlyFinanceReport.add(report);
        }

        return monthlyFinanceReport;
    }
}
