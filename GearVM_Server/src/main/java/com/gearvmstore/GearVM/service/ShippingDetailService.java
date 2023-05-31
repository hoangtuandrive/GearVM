package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.ShippingDetail;
import com.gearvmstore.GearVM.repository.ShippingDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class ShippingDetailService {
    private final ShippingDetailRepository shippingDetailRepository;

    public ShippingDetailService(ShippingDetailRepository shippingDetailRepository) {
        this.shippingDetailRepository = shippingDetailRepository;
    }

    public ShippingDetail updateOnlineShippingDetail(ShippingDetail shippingDetail, String customerName, String address,
                                                     String email, String phoneNumber, double shippingCost) {
        shippingDetail.setName(customerName);
        shippingDetail.setAddress(address);
        shippingDetail.setEmail(email);
        shippingDetail.setPhoneNumber(phoneNumber);
        shippingDetail.setShippingCost(shippingCost);

        return shippingDetailRepository.save(shippingDetail);
    }
}
