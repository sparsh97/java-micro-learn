package verma.sparsh.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String email;
    private Double amount;
    private String paymentType;
    private String paymentMethod;
    private String srcAccount;
    private Double availableAmount;
}
