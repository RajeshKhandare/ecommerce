package com.demo.serviceimpl;

import com.demo.dto.OrderRequest;
import com.demo.dto.OrderResponse;
import com.demo.model.Item;
import com.demo.model.Order;
import com.demo.repository.ItemRepository;
import com.demo.repository.OrderRepository;
import com.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Create and save a new order
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        List<Item> items = itemRepository.findAllById(request.getItemIds());

        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .items(items)
                .orderDate(LocalDateTime.now())
                .status("PLACED")
                .build();

        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    // Get all orders
    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // Get specific order by ID
    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponse(order);
    }

    // Update order status
    @Override
    public OrderResponse updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return mapToResponse(updated);
    }

    // Cancel an order (soft delete approach)
    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }

    // Helper method to convert Order entity to DTO
    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .itemNames(order.getItems().stream().map(Item::getName).collect(Collectors.toList()))
                .build();
    }
}
