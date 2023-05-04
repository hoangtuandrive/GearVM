package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Employee;
import com.gearvmstore.GearVM.model.Product;
import com.gearvmstore.GearVM.model.Purchase;
import com.gearvmstore.GearVM.model.dto.purchase.CreatePurchase;
import com.gearvmstore.GearVM.model.response.EmployeeResponseModel;
import com.gearvmstore.GearVM.model.response.GetPurchaseListResponse;
import com.gearvmstore.GearVM.model.response.ProductResponseModel;
import com.gearvmstore.GearVM.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        if (productService.addQuantity(product, createPurchase.getQuantity()) != null)
            return purchaseRepository.save(purchase);
        return null;
    }

    public List<GetPurchaseListResponse> getPurchases() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        List<GetPurchaseListResponse> getPurchaseListResponseList = new ArrayList<>();

        for (Purchase purchase : purchaseList) {
            GetPurchaseListResponse getPurchaseListResponse = modelMapper.map(purchase, GetPurchaseListResponse.class);
            getPurchaseListResponse.setEmployeeResponseModel(modelMapper.map(purchase.getEmployee(), EmployeeResponseModel.class));
            getPurchaseListResponse.setProductResponseModel(modelMapper.map(purchase.getProduct(), ProductResponseModel.class));
            getPurchaseListResponseList.add(getPurchaseListResponse);
        }
        return getPurchaseListResponseList;
    }

    public List<GetPurchaseListResponse> getPurchasesByFilter(String id, String employeeId, String employeeName, String productId, String productName) {
        try {
            Long parsedId = Long.parseLong(id);
            Long parsedEmployeeId = Long.parseLong(employeeId);
            Long parsedProductId = Long.parseLong(productId);

            List<Purchase> purchaseList = purchaseRepository.findDistinctByIdEqualsOrEmployee_IdEqualsOrEmployee_NameContainingIgnoreCaseOrProduct_IdEqualsOrProduct_NameContainingIgnoreCaseOrderByIdAsc
                    (parsedId, parsedEmployeeId, employeeName, parsedProductId, productName);
            List<GetPurchaseListResponse> getPurchaseListResponseList = new ArrayList<>();

            for (Purchase purchase : purchaseList) {
                GetPurchaseListResponse getPurchaseListResponse = modelMapper.map(purchase, GetPurchaseListResponse.class);
                getPurchaseListResponse.setEmployeeResponseModel(modelMapper.map(purchase.getEmployee(), EmployeeResponseModel.class));
                getPurchaseListResponse.setProductResponseModel(modelMapper.map(purchase.getProduct(), ProductResponseModel.class));
                getPurchaseListResponseList.add(getPurchaseListResponse);
            }
            return getPurchaseListResponseList;
        } catch (NumberFormatException e) {
            List<Purchase> purchaseList = purchaseRepository.findDistinctByIdEqualsOrEmployee_IdEqualsOrEmployee_NameContainingIgnoreCaseOrProduct_IdEqualsOrProduct_NameContainingIgnoreCaseOrderByIdAsc
                    (null, null, employeeName, null, productName);
            List<GetPurchaseListResponse> getPurchaseListResponseList = new ArrayList<>();

            for (Purchase purchase : purchaseList) {
                GetPurchaseListResponse getPurchaseListResponse = modelMapper.map(purchase, GetPurchaseListResponse.class);
                getPurchaseListResponse.setEmployeeResponseModel(modelMapper.map(purchase.getEmployee(), EmployeeResponseModel.class));
                getPurchaseListResponse.setProductResponseModel(modelMapper.map(purchase.getProduct(), ProductResponseModel.class));
                getPurchaseListResponseList.add(getPurchaseListResponse);
            }
            return getPurchaseListResponseList;
        }

    }
}
