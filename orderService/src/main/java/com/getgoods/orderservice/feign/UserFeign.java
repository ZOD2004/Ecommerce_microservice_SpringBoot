package com.getgoods.orderservice.feign;

import com.getgoods.orderservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {
    @GetMapping("/api/user/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id);
}
