package verma.sparsh.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import verma.sparsh.user.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
