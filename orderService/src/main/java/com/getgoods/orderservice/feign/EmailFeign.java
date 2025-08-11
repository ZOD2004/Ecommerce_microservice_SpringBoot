package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name  = "EMAIL-SERVICE")
public interface EmailFeign {

    @PostMapping("api/email/sendOrderConfirmation")
    public ResponseEntity<String> sendOrderEmail(
            @RequestBody Order order,
            @RequestParam String toEmail);

}
