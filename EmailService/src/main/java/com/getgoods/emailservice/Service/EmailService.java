package com.getgoods.emailservice.Service;

import com.getgoods.emailservice.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendOrderConfirmationEmail(@RequestBody Order order, String recipientEmail) {

        StringBuilder body = new StringBuilder();
        body.append("Hello,\n\n");
        body.append("Thank you for your order #").append(order.getOrderId()).append(".\n");
        body.append("Status: ").append(order.getStatus()).append("\n");
        body.append("Total Amount: ").append(order.getTotalAmount()).append("\n");
        body.append("Shipping Address: ").append(order.getAddress()).append("\n\n");
        body.append("Ordered items:\n");
        order.getOrderItems().forEach(item -> {
            body.append("- Product ID: ").append(item.getProductId())
                    .append(", Quantity: ").append(item.getQuantity())
                    .append(", Unit Price: ").append(item.getUnitPrice()).append("\n");
        });
        body.append("\nThank you for shopping with us!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Order Confirmation #" + order.getOrderId());
        message.setText(body.toString());
        mailSender.send(message);
    }
}
