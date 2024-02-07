package rabbit.niloapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rabbit.niloapi.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
