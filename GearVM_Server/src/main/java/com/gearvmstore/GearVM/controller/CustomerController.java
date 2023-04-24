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
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=http://localhost:8080/api/" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
    @PostMapping("/forgot_password")
    public ResponseEntity<String> processForgotPassword(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {

        String token = RandomString.make(30);
        customerService.updateResetPasswordToken(token, email);
        String resetPasswordLink =  "/reset_password?token=" + token;
        sendEmail(email, resetPasswordLink);

        return ResponseEntity.ok().body(resetPasswordLink);
    }


}