package com.demo.service;

import com.demo.dto.OrderRequest;
import com.demo.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrderStatus(Long id, String status);

    void cancelOrder(Long id);
}
