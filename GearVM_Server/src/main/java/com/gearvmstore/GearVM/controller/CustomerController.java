package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.Customer;
import com.gearvmstore.GearVM.model.dto.user.LoginDto;
import com.gearvmstore.GearVM.model.dto.user.RegisterDto;
import com.gearvmstore.GearVM.service.CustomerService;
import com.gearvmstore.GearVM.utility.JwtUtil;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public CustomerController(CustomerService customerService, JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = modelMapper.map(registerDTO, Customer.class);

        Customer newAccount = customerService.register(customer);
        if (newAccount == null) {
            return ResponseEntity.badRequest().body("Account existed");
        }

        return ResponseEntity.ok().body(newAccount);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("get-all-filter")
    public List<Customer> getAllCustomersByFilter(@RequestParam(defaultValue = "") String filter) {
        return customerService.getCustomersByFilter(filter, filter, filter, filter);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Customer customer = customerService.validateLogin(loginDTO.getUsername(), loginDTO.getPassword());

        if (customer == null) {
            return ResponseEntity.badRequest().body("Login failed");
        }

        return ResponseEntity.ok().body(customerService.generateToken(customer.getId().toString(), customer.getEmail()));
    }

    @GetMapping(value = "/email-exist/{email}")
    public ResponseEntity<String> checkEmailExist(@PathVariable(value = "email") String email) {
        if (customerService.checkEmailExist(email)) return ResponseEntity.ok().body("true");
        else return ResponseEntity.ok().body("false");
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customerService.createCustomer(c), HttpStatus.OK);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "customerId") Long id, @RequestBody Customer customerDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerDetails));
    }

    @GetMapping(value = "/current-user")
    public ResponseEntity<?> checkCurrentUser(@RequestHeader(name = "Authorization") String header) {
        if (header == null)
            return new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);

        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token))
            return ResponseEntity.ok().body(customerService.getCustomer(Long.parseLong(jwtUtil.getIdFromToken(token))));
        return new ResponseEntity<String>("Token expired", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/phonenumber/{phoneNumber}")
    public ResponseEntity<?> getCustomerByPhoneNumber(@PathVariable(value = "phoneNumber") String phoneNumber) {
        Customer customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        if (customer == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping(value = "/get-all-phoneNumber")
    public ResponseEntity<?> getAllPhoneNumber() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllByPhoneNumber());
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tranhoanglong261220000@gmail.com", "Hoang Long Tran ");
        helper.setTo(recipientEmail);

        String subject = "Đây là OTP để đặt lại mật khẩu của bạn";

        String content = "<p>Xin chào,</p>"
                + "<p>Bạn đã yêu cầu đặt lại mật khẩu của mình.</p>"
                + "<p>Đây là mã OTP để đặt lại mật khẩu của bạn </p>"
                + "<p>" + link + "</p>"
                + "<br>"
                + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
                + "hoặc bạn chưa thực hiện yêu cầu.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> processForgotPassword(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        Customer customer = customerService.getEmailResetPassWord(email);
        System.out.println(email);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Email này không tồn tại");
        }

        customer.setResetPasswordToken(null);

        String token = RandomString.make(30);
        customerService.updateResetPasswordToken(token, email);
        String resetPasswordLink = token;
        sendEmail(email, resetPasswordLink);

        return ResponseEntity.ok().body(resetPasswordLink);
    }

    @GetMapping("/Check-tokenforgot/{token}")
    public ResponseEntity<String> CheckTokenForgot(@PathVariable(value = "token") String token) {

        Customer customer = customerService.getByResetPasswordToken(token);

        if (customer == null) {
            return ResponseEntity.badRequest().body("OTP của bạn đã hết hạn hoặc bạn đã nhập sai");
        }
        return ResponseEntity.ok().body(token);
    }

    @PutMapping("/reset_password/{token}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "token") String token, @RequestBody String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        Customer customer = customerService.getByResetPasswordToken(token);
        System.out.println(token);
        System.out.println("asd" + password);
        if (customer == null) {
            return ResponseEntity.badRequest().body("OTP của bạn đã hết hạn hoặc bạn đã nhập sai");
        } else {
            customerService.updatePassword(customer, password);
            return ResponseEntity.ok().body(customer);
        }

    }

}