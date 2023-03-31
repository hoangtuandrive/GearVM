package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.Purchase;
import com.gearvmstore.GearVM.model.dto.purchase.CreatePurchase;
import com.gearvmstore.GearVM.model.response.GetPurchaseListResponse;
import com.gearvmstore.GearVM.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public PurchaseService(PurchaseRepository purchaseRepository, EmployeeService employeeService, ProductService productService, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.employeeService = employeeService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    public Purchase createPurchase(CreatePurchase createPurchase) {
        Product product = productService.getProduct(createPurchase.getProductId());
        Employee employee = employeeService.getEmployee(createPurchase.getEmployeeId());

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setEmployee(employee);
        purchase.setCreatedDate(LocalDateTime.now());
        purchase.setPrice(createPurchase.getPrice());
        purchase.setQuantity(createPurchase.getQuantity());
        return purchaseRepository.save(purchase);
    }

    public List<GetPurchaseListResponse> getPurchases() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        return Arrays.asList(modelMapper.map(purchaseList, GetPurchaseListResponse[].class));
    }
}
