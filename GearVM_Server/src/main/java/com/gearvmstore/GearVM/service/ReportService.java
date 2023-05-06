package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.response.MostSoldProductResponseModel;
import com.gearvmstore.GearVM.repository.OrderItemRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import com.gearvmstore.GearVM.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public ReportService(ModelMapper modelMapper, ProductService productService, ProductRepository productRepository, OrderService orderService, OrderRepository orderRepository,
                         OrderItemRepository orderItemRepository) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.productRepository = productRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<MostSoldProductResponseModel> getMostSoldItems() {
        Pageable pageable = PageRequest.of(0, 5);
        return orderItemRepository.findMostSoldItems(pageable);
    }


}
