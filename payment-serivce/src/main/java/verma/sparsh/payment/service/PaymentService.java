package verma.sparsh.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import verma.sparsh.payment.entity.Payment;
import verma.sparsh.payment.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
