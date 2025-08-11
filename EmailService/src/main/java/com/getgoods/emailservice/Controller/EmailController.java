package com.getgoods.emailservice.Controller;

import com.getgoods.emailservice.Model.Order;
import com.getgoods.emailservice.Service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private JavaMailSender mailSender;
    private EmailService emailService;
    public EmailController(JavaMailSender mailSender, EmailService emailService) {
        this.mailSender = mailSender;
        this.emailService = emailService;
    }
    @PostMapping("/sendOrderConfirmation")
    public ResponseEntity<String> sendOrderEmail(
            @RequestBody Order order,
            @RequestParam String toEmail) {
        emailService.sendOrderConfirmationEmail(order, toEmail);
        return ResponseEntity.ok("Order confirmation email sent.");
    }
}
