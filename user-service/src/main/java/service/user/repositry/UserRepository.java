package service.user.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import service.user.entity.Users;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
}
