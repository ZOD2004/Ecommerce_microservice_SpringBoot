package com.getgoods.cartservice.Feign;

import com.getgoods.cartservice.Model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeign {
    @GetMapping("/api/product/find/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId") Long productId);
}
