package com.getgoods.cartservice.Service.ServiceImplementation;

import com.getgoods.cartservice.Feign.InventoryFeign;
import com.getgoods.cartservice.Feign.ProductFeign;
import com.getgoods.cartservice.Feign.UserFeign;
import com.getgoods.cartservice.Model.Inventory;
import com.getgoods.cartservice.Model.Product;
import com.getgoods.cartservice.Model.User;
import com.getgoods.cartservice.Repository.CartItemRepository;
import com.getgoods.cartservice.Repository.CartRepository;
import com.getgoods.cartservice.Service.CartService;
import com.getgoods.cartservice.Entity.Cart;
import com.getgoods.cartservice.Entity.CartItem;
import com.getgoods.cartservice.Util.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService {

    private CartRepository cartRepository;
    private UserFeign userFeign;
    private InventoryFeign inventoryFeign;
    private ProductFeign productFeign;
    private CartItemRepository  cartItemRepository;

    public CartServiceImplementation(CartRepository cartRepository,UserFeign userFeign,
                                     InventoryFeign inventoryFeign,ProductFeign productFeign,CartItemRepository cartItemRepository){
        this.cartRepository=cartRepository;
        this.userFeign=userFeign;
        this.inventoryFeign=inventoryFeign;
        this.productFeign=productFeign;
        this.cartItemRepository=cartItemRepository;
    }

    @Override
    public Cart createCart(Long userId) {
        User user=null;
        try{
            user = userFeign.getUserById(userId).getBody();
        }catch (UserFeignException e){
            throw new UserFeignException(e.getMessage()+"user not found with id "+userId);
        }
        if(user != null){
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setCreatedAt(new Date());
            cart.setCartItems(new ArrayList<>());
            return cartRepository.save(cart);
        }
        else{
            throw new CartNotFoundException("Cart Not created for id "+userId);
        }
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(cart.isPresent()){
            return cart.get();
        }
        else{
            return createCart(userId);
        }
    }
    //working
    @Override
    public CartItem addItemToCart(Long userId, Long productId, Long quantity) {
        User user=null;
        try {
            user = userFeign.getUserById(userId).getBody();
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        Product product;
        try {
            product = productFeign.findById(productId).getBody();
        } catch (Exception e) {
            throw new ProductUnavailableException("Product not found with id " + productId);
        }

        Inventory inventory = null;
        try{
            inventory = inventoryFeign.findInventoryByProductId(productId).getBody();
        }catch (InventoryNotFoundException e){
            throw new InventoryNotFoundException("Inventory not found for productId " + productId);
        }

        if (!inventoryFeign.isInStock(productId, quantity).getBody()) {
            throw new InventoryNotFoundException("Insufficient inventory for product " + productId);
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));

        Optional<CartItem> optionalItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);

        if (optionalItem.isPresent()) {
            CartItem existingItem = optionalItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
//            inventoryFeign.reduceStock(productId,quantity);
            return cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
//            inventoryFeign.reduceStock(productId,quantity);
            return cartItemRepository.save(newItem);
        }
    }


    @Override
    public CartItem updateCartItem(Long userId, Long productId, Long quantity) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (!cart.isPresent()) {
            throw new CartNotFoundException("Cart not found for userId " + userId);
        }

        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndProductId(cart.get().getId(), productId);
        if (cartItem.isPresent()) {
            CartItem existingItem = cartItem.get();
//            if(quantity > existingItem.getQuantity()){
//                inventoryFeign.reduceStock(productId,existingItem.getQuantity() - quantity);
//            }
//            else{
//                inventoryFeign.addStock(productId,Math.abs(quantity - existingItem.getQuantity()));
//            }
            existingItem.setQuantity(quantity);
            return cartItemRepository.save(existingItem);
        } else {
            throw new CartItemNotFoundException("Cart Item not found for productId " + productId);
        }
    }

    @Transactional
    @Override
    public void removeItemFromCart(Long userId, Long productId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartItem> cartItems = cart.getCartItems();

            CartItem toRemove = null;

            for (CartItem item : cartItems) {
                if (item.getProductId().equals(productId)) {
                    toRemove = item;
                    break;
                }
            }

            if (toRemove != null) {
//                inventoryFeign.addStock(productId,toRemove.getQuantity());//adding the removed item to cart
                cartItems.remove(toRemove);
                cartRepository.save(cart);
            } else {
                throw new CartItemNotFoundException("No cart item with productId " + productId + " found in cart.");
            }

        } else {
            throw new CartNotFoundException("Cart not found for userId " + userId);
        }
    }


    @Override
    @Transactional
    public void clearCart(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if(optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            List<CartItem> cartItems = cart.getCartItems();
//            for (CartItem item : cartItems) {
//                inventoryFeign.addStock(item.getProductId(), item.getQuantity());
//            }
            cart.getCartItems().clear();
            cartRepository.save(cart);
        }
        else{
            throw new CartNotFoundException("Cart not found for userId " + userId);
        }
    }

    @Override
    public List<CartItem> getAllItemsInCart(Long userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if(optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            return cart.getCartItems();
        }
        else{
            throw new CartNotFoundException("Cart not found for userId " + userId);
        }

    }
}
