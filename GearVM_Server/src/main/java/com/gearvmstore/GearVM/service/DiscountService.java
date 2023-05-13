package com.gearvmstore.GearVM.service;

import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.Discount;
import com.gearvmstore.GearVM.model.Order;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.dto.order.UpdateOrderStatusAndEmployee;
import com.gearvmstore.GearVM.repository.CustomerRepository;
import com.gearvmstore.GearVM.repository.DiscountRepository;
import com.gearvmstore.GearVM.repository.OrderRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    @Autowired
    private JavaMailSender mailSender;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository,
                           OrderRepository orderRepository,
                           CustomerRepository customerRepository) {
        this.discountRepository = discountRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }
    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tranhoanglong261220000@gmail.com", "Hoang Long Tran ");
        helper.setTo(recipientEmail);

        String subject = "Đây là mã  Giảm giá mà cửa hàng chúng tôi muốn chi ân bạn";

        String content = "<p>Xin chào,</p>"
                + "<p>Cảm ơn bạn đã mua hàng với đơn giá hơn 10 triệu đồng.</p>"
                + "<p>Chúng tôi xin gửi bạn mã giảm giá</p>"
                + "<p>" + link + "</p>"
                + "<br>"
                + "<p>Lưu ý mã giảm giá chỉ cho phép sử trong vòng 1 tháng , "
                + "nếu bạn không sử dụng sẽ hết hạn.</p>";

        helper.setSubject(subject); 

        helper.setText(content, true);

        mailSender.send(message);
    }
    public void SendDiscount(Long orderId, UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee) throws MessagingException, UnsupportedEncodingException {
        Order order=orderRepository.findById(orderId).get();
        String email= order.getCustomer().getEmail();
        String code = RandomString.make(30);
        double totalPrice= order.getTotalPrice();
        OrderStatus orderStatus=updateOrderStatusAndEmployee.getOrderStatus();
        System.out.println(email);
        if(totalPrice>=10000 &&  orderStatus == orderStatus.SHIP_SUCCESS && email !=null ){
            Discount discount=new Discount(10,10, LocalDateTime.now().plusMonths(1),false,code);
            sendEmail(email,code);
            discountRepository.save(discount);
        }
    }
    public double GetPercentDiscount(String code){
       Discount discount= discountRepository.findByCode(code);
       if(discount != null ){
           double percent= discount.getPercentageDiscount();
           if(discount.getExpirationDate().compareTo(LocalDateTime.now())>0 && discount.isUsed()==false){
               return  percent;
           }
       }
       return 0;
    }
    public Discount  UpdateisUsedDiscount(Long orderId, UpdateOrderStatusAndEmployee updateOrderStatusAndEmployee){
        Order order=orderRepository.findById(orderId).get();
        OrderStatus orderStatus=updateOrderStatusAndEmployee.getOrderStatus();

        Discount discount=discountRepository.findById(order.getDiscount().getId()).get();

        if(orderStatus == orderStatus.SHIP_SUCCESS){
            discount.setUsed(true);
            return discountRepository.save(discount);
        }
        else if(orderStatus == orderStatus.REJECTED ){
            discount.setUsed(false);
            return discountRepository.save(discount);
        }
        return null;
    }
    public Discount GetIdDiscount(Long orderId){
        Order order=orderRepository.findById(orderId).get();
        Discount discountOrder = order.getDiscount();
        if(discountOrder != null){
            Long discountId= discountOrder.getId();
            Discount discount=discountRepository.findById(discountId).get();
            return  discount;
        }
        return null;
    }
}
