package verma.sparsh.payment.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Order {

    private Integer id;
    private String name;
    private String category;
    private Double price;
    private Date purchaseDate;
    private String orderId;
    private Integer userId;
    private String paymentMode;

}