package verma.sparsh.payment.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import verma.sparsh.payment.dto.Order;
import verma.sparsh.payment.dto.Users;
import verma.sparsh.payment.entity.Payment;
import verma.sparsh.payment.repository.PaymentRepository;

import java.time.Instant;
import java.util.Date;

@Component
public class OrderProcessingConsumer {

    public static final String HTTP_LOCALHOST_8080_USERS = "http://localhost:8080/user/";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "ORDER_PAYMENT_TOPIC")
    public void processOrder(String orderJSON) {
        try {
            Order order = new ObjectMapper().readValue(orderJSON, Order.class);
            Payment paymentReq = Payment.builder()
                    .payMode(order.getPaymentMode())
                    .amount(order.getPrice())
                    .paidDate(new Date())
                    .userId(order.getUserId())
                    .orderId(order.getOrderId())
                    .build();
            if (paymentReq.getPayMode().equalsIgnoreCase("cod")) {
                paymentReq.setPayMode("PENDING");
            } else {
                // do rest call to user service
                Users user = restTemplate.getForObject(HTTP_LOCALHOST_8080_USERS +paymentReq.getUserId(), Users.class);
                if (paymentReq.getAmount() > user.getAmount()) {
                    throw new RuntimeException("Insufficient balance");
                } else {
                    paymentReq.setPaymentStatus("PAID");
                    paymentRepository.save(paymentReq);
                    restTemplate.put(HTTP_LOCALHOST_8080_USERS+"update/amount/" + paymentReq.getUserId() + "/"+
                            paymentReq.getAmount(), null);
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
