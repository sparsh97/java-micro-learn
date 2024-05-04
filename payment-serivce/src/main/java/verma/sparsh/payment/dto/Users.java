package verma.sparsh.payment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    private int id;
    private String name;
    private String email;
    private Double amount;
    private String paymentType;
    private String paymentMethod;
    private String srcAccount;
    private Double availableAmount;
}