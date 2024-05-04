package verma.sparsh.order.service;

import verma.sparsh.order.dto.OrderResponseDto;
import verma.sparsh.order.entity.Order;

public interface OrderService {

    public String placeOrder(Order order);

    public OrderResponseDto getOrder(String orderId);
}
