package verma.sparsh.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import verma.sparsh.order.dto.OrderResponseDto;
import verma.sparsh.order.dto.PaymentDTO;
import verma.sparsh.order.dto.UserDTO;
import verma.sparsh.order.entity.Order;
import verma.sparsh.order.repository.OrderRepositoy;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepositoy orderRepositoy;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${order.producer.topic.name}")
    private String topicName;

    @Autowired
    public OrderServiceImpl(OrderRepositoy orderRepositoy) {
        this.orderRepositoy = orderRepositoy;
    }


    @Override
    public String placeOrder(Order order) {
        log.info("OrderServiceImpl::placeOrder initiated");
        //save in db of order service db
        order.setPurchaseDate(new Date());
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        order = orderRepositoy.save(order);
        //send it to payment service using kafka
        try {
            kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("OrderServiceImpl::placeOrder finished");
        return "Your order has been placed with + " + order.getOrderId() + ". We will notify once payment is verified.";
    }

    @Override
    public OrderResponseDto getOrder(String orderId) {
        try {
            Order order = orderRepositoy.findByOrderId(orderId);
            PaymentDTO paymentDTO = restTemplate.getForObject("http://localhost:9292/payments/" + orderId, PaymentDTO.class);
            UserDTO userDTO = restTemplate.getForObject("http://localhost:8080/user/" + order.getUserId(), UserDTO.class);
            return OrderResponseDto.builder()
                    .paymentDTO(paymentDTO)
                    .userDTO(userDTO)
                    .order(order)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching details");
        }
    }


}
