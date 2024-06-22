package service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    public UUID id;
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String phone;
    public String password;
    public Boolean status;
    public BigDecimal amount;
    public String createdBy;
    public String updatedBy;
    public Instant createdDate;
    public Instant updatedDate;

}
