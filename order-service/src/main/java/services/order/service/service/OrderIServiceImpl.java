package services.order.service.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.order.service.dto.OrderDTO;
import services.order.service.entity.Order;
import services.order.service.mapper.OrderMapper;
import services.order.service.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderIServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Creating order: {}", orderDTO);
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        log.info("Getting order by id: {}", orderId);
        return orderMapper
                .toDTO(orderRepository.findById(UUID.fromString(orderId)).orElse(null));
    }

    @Override
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) {
        log.info("Updating order: {}", orderDTO);
        Optional<Order> order = orderRepository.findById(UUID.fromString(orderId));
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setCustomerId(orderDTO.getCustomerId());
            updatedOrder.setProductId(orderDTO.getProductId());
            updatedOrder.setQuantity(orderDTO.getQuantity());
            updatedOrder.setPrice(orderDTO.getPrice());
            updatedOrder.setStatus(orderDTO.getStatus());
            return orderMapper.toDTO(orderRepository.save(updatedOrder));
        }
        throw new RuntimeException("Order not found");
    }

    @Override
    public void deleteOrder(String orderId) {
        log.info("Deleting order by id: {}", orderId);
        orderRepository.deleteById(UUID.fromString(orderId));
    }

    @Override
    public List<OrderDTO> getOrders() {
        log.info("Getting all orders");
        return orderMapper.toDTO(orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(String customerId) {
        log.info("Getting orders by customer id: {}", customerId);
        return orderMapper.toDTO(orderRepository.findByCustomerId(customerId));
    }
}
