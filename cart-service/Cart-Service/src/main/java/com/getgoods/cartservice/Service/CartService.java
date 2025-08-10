package com.getgoods.cartservice.Service;

import com.getgoods.cartservice.Entity.Cart;
import com.getgoods.cartservice.Entity.CartItem;

import java.util.List;

public interface CartService {

        Cart createCart(Long userId);

        Cart getCartByUserId(Long userId);

        CartItem addItemToCart(Long userId, Long productId, Long quantity);

        CartItem updateCartItem(Long userId, Long productId, Long quantity);

        void removeItemFromCart(Long userId, Long productId);

        void clearCart(Long userId);

        List<CartItem> getAllItemsInCart(Long userId);

}
