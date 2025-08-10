package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.model.Cart;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CART-SERVICE")
public interface CartFeign {
    @GetMapping("/api/cart/find/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId")Long userId);
    @DeleteMapping("/api/cart/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable("userId")  Long userId);
}
