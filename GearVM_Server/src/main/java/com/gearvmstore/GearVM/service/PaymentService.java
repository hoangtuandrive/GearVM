package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Payment;
import com.gearvmstore.GearVM.model.PaymentMethod;
import com.gearvmstore.GearVM.repository.PaymentRepository;
import com.gearvmstore.GearVM.repository.ShippingDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;


    public PaymentService(PaymentRepository paymentRepository, ShippingDetailRepository shippingDetailRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment updateOnlinePayment(Payment payment, String paymentDescription) {
        payment.setPaymentDescription(paymentDescription);
        payment.setPaymentMethod(PaymentMethod.STRIPE);

        return paymentRepository.save(payment);
    }
}
