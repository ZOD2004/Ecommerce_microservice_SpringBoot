package com.getgoods.orderservice.service.orderServiceImple;

import com.getgoods.orderservice.entity.Order;
import com.getgoods.orderservice.entity.OrderItem;
import com.getgoods.orderservice.entity.OrderStatus;
import com.getgoods.orderservice.feign.CartFeign;
import com.getgoods.orderservice.feign.InventoryFeign;
import com.getgoods.orderservice.feign.ProductFeign;
import com.getgoods.orderservice.model.BulkStock;
import com.getgoods.orderservice.model.Cart;
import com.getgoods.orderservice.model.CartItem;
import com.getgoods.orderservice.model.StockItem;
import com.getgoods.orderservice.repository.OrderRepository;
import com.getgoods.orderservice.service.OrderService;
import com.getgoods.orderservice.util.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImple implements OrderService {

    private OrderRepository orderRepository;
    private CartFeign cartFeign;
    private ProductFeign productFeign;
    private InventoryFeign inventoryFeign;
    public OrderServiceImple(OrderRepository orderRepository,CartFeign cartFeign,ProductFeign productFeign,InventoryFeign inventoryFeign) {
        this.orderRepository = orderRepository;
        this.cartFeign = cartFeign;
        this.productFeign = productFeign;
        this.inventoryFeign = inventoryFeign;
    }

    @Override
    @Transactional
    public Order createOrderFromCart(Long userId, String address) {
        Cart cart = Optional.ofNullable(cartFeign.getCartByUserId(userId).getBody())
                .orElseThrow(() -> new CartNotFoundException("Cart with userId " + userId + " not found"));

        if (cart.getCartItems().isEmpty()) {
            throw new OrderNotMadeException("Cart is empty");
        }

        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());
        order.setUpdatedAt(new Date());

        List<OrderItem> orderItems = new ArrayList<>();
        Long totalPrice = 0L;

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            Long price = productFeign.findById(cartItem.getProductId()).getBody().getPrice();
            orderItem.setUnitPrice(price);
            totalPrice += price * cartItem.getQuantity();
            orderItems.add(orderItem);
        }
        //reduce inventory
        //change tot eff to check if that working
        boolean f = inventoryReduction(orderItems);

        if(!f){
            throw new OrderNotMadeException("Unfortunately order has not been made....");
        }

        order.setUserId(userId);
        order.setCartId(cart.getId());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setUpdatedAt(new Date());
        order.setAddress(address);
        order.setTotalAmount(totalPrice);
        order.setOrderItems(orderItems);

        orderRepository.save(order);
        cartFeign.clearCart(userId);
        return order;
    }

    private boolean inventoryReduction(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            if (!inventoryFeign.isInStock(orderItem.getProductId(), orderItem.getQuantity()).getBody()) {
                throw new RanOutOfProductException("Product " + orderItem.getProductId() + " is out of stock");
            }
        }

        for (OrderItem orderItem : orderItems) {
            inventoryFeign.reduceStock(orderItem.getProductId(), orderItem.getQuantity());
        }
        return true;
    }

    private boolean inventoryReductionEff(List<OrderItem> orderItems) {
        BulkStock bulkStock = new BulkStock();
        List<StockItem>l=new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            StockItem stockItem = new StockItem();
            stockItem.setProductId(orderItem.getProductId());
            stockItem.setQuantity(orderItem.getQuantity());
            l.add(stockItem);
        }
        bulkStock.setStockItems(l);
        if(Boolean.TRUE.equals(inventoryFeign.checkInBulk(bulkStock).getBody())){
            inventoryFeign.reduceInBulk(bulkStock);
            return true;
        }
        return false;
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if ((optionalOrder.isPresent())) {
            return optionalOrder.get();
        }
        else{
            throw new OrderNotFoundException("order with id " + orderId + " not found");
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        Optional<List<Order>> order = orderRepository.findAllByUserId(userId);
        if(order.isPresent()){
            return order.get();
        }
        else{
            throw new UserNotFoundException("order with id " + userId + " not found");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();

    }


    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        Optional<List<Order>> optionalOrders = orderRepository.findOrderByStatus(status);
        if(optionalOrders.isPresent()){
            return optionalOrders.get();
        }
        else{
            throw new OrderNotFoundException("order with status " + status + " not found");
        }
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            BulkStock bulkStock = new BulkStock();
            List<StockItem>l=new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {
                StockItem stockItem = new StockItem();
                stockItem.setProductId(orderItem.getProductId());
                stockItem.setQuantity(orderItem.getQuantity());
                l.add(stockItem);
            }
            bulkStock.setStockItems(l);
            inventoryFeign.addInBulk(bulkStock);
            orderRepository.deleteById(orderId);
        }
        else{
            throw new OrderNotFoundException("order with id " + orderId + " not found");
        }
    }

    @Override
    public Long getOrderCountByUser(Long userId) {
        Optional<List<Order>>orders = orderRepository.findAllByUserId(userId);
        if(orders.isPresent()){
            return (long) orders.get().size();
        }else{
            throw new OrderNotFoundException("order with id " + userId + " not found");
        }
    }

    @Override
    public Long getTotalOrderCount() {
        return (long) orderRepository.findAll().size();
    }

    @Override
    public Long getTotalRevenueByUser(Long userId) {
        Optional<List<Order>>orders = orderRepository.findAllByUserId(userId);
        if(orders.isPresent()){
            Long tot=0L;
            for(Order order : orders.get()){
                tot+=order.getTotalAmount();
            }
            return tot;
        }
        else{
            return 0L;
        }
    }

    @Override
    public Long getTotalRevenue() {
        List<Order>orders = orderRepository.findAll();
        Long tot=0L;
        for(Order order : orders){
            tot+=order.getTotalAmount();
        }
        return tot;
    }

//    @Override
//    public List<Order> getOrdersByDateRange(Date startDate, Date endDate) {
//        Optional<List<Order>> orders = orderRepository.findOrderByDateRange(startDate,endDate);
//        if(orders.isPresent()){
//            return orders.get();
//        }
//        else{
//            throw new OrderNotFoundException("No order with between "+startDate+" and "+endDate);
//        }
//    }
}
