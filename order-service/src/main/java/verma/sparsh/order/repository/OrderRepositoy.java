package verma.sparsh.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import verma.sparsh.order.entity.Order;

@Repository
public interface OrderRepositoy extends JpaRepository<Order, Integer> {
    Order findByOrderId(String orderId);
}
