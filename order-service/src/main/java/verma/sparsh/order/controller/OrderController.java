package verma.sparsh.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import verma.sparsh.order.dto.OrderResponseDto;
import verma.sparsh.order.entity.Order;
import verma.sparsh.order.service.OrderService;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public String placeNewOrder(@RequestBody Order order) {
        log.info("OrderController::placeNewOrder is called");
        return orderService.placeOrder(order);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@PathVariable("id") String orderId) {
        return orderService.getOrder(orderId);
    }
}
