package verma.sparsh.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import verma.sparsh.payment.entity.Payment;
import verma.sparsh.payment.service.PaymentService;

@RestController
@RequestMapping(path = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId) {
        return paymentService.getByOrderId(orderId);
    }
}
