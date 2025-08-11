package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.model.BulkStock;
import com.getgoods.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductFeign {
    @GetMapping("api/product/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id);


}
