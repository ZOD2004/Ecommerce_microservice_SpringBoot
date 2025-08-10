package com.getgoods.cartservice.Controller;

import com.getgoods.cartservice.Entity.Cart;
import com.getgoods.cartservice.Entity.CartItem;
import com.getgoods.cartservice.Feign.ProductFeign;
import com.getgoods.cartservice.Model.Category;
import com.getgoods.cartservice.Model.Product;
import com.getgoods.cartservice.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private ProductFeign productFeign;

    public CartController(CartService cartService,ProductFeign productFeign) {
        this.cartService = cartService;
        this.productFeign = productFeign;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(
            @RequestParam @NotNull @Positive Long userId) {
        return new ResponseEntity<>(cartService.createCart(userId), HttpStatus.CREATED); }

    @GetMapping("/find/{userId}")
    public ResponseEntity<Cart> getCartByUserId(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(cartService.getCartByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestParam @NotNull @Positive Long userId,
            @RequestParam @NotNull @Positive Long productId,
            @RequestParam @NotNull @Min(1) Long quantity) {
        return new ResponseEntity<>(cartService.addItemToCart(userId, productId, quantity), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CartItem> updateCartItem(
            @RequestParam @NotNull @Positive Long userId,
            @RequestParam @NotNull @Positive Long productId,
            @RequestParam @NotNull Long quantity) {
        return new ResponseEntity<>(cartService.updateCartItem(userId, productId, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam @NotNull @Positive Long userId,
            @RequestParam @NotNull @Positive Long productId) {
        cartService.removeItemFromCart(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<List<CartItem>> getAllItemsInCart(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        return new ResponseEntity<>(cartService.getAllItemsInCart(userId), HttpStatus.OK);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getCartItemCount(
            @PathVariable("userId") @NotNull @Positive Long userId) {
        List<CartItem> items = cartService.getAllItemsInCart(userId);
        Long totalCount = items.stream().mapToLong(CartItem::getQuantity).sum();
        return new ResponseEntity<>(totalCount, HttpStatus.OK);
    }

//debugging rest
    @GetMapping("/demo/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
       Product product= productFeign.findById(id).getBody();
       return new ResponseEntity<>(product, HttpStatus.OK);
    }
}