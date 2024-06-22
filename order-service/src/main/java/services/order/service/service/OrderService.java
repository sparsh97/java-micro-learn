package services.order.service.service;

import services.order.service.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrder(String orderId);
    OrderDTO updateOrder(String orderId, OrderDTO orderDTO);
    void deleteOrder(String orderId);
    List<OrderDTO> getOrders();
    List<OrderDTO> getOrdersByCustomerId(String customerId);
}
