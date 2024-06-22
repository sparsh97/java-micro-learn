package service.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Users implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
    private BigDecimal amount;
    private String createdBy;
    private String updatedBy;

    @CreationTimestamp(source = SourceType.DB)
    private Instant createdDate;
    @UpdateTimestamp(source = SourceType.DB)
    private Instant updatedDate;

}
