package verma.sparsh.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import verma.sparsh.order.entity.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Order order;
    private PaymentDTO paymentDTO;
    private UserDTO userDTO;

}
