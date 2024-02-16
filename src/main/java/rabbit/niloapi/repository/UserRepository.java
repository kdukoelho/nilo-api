package rabbit.niloapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import rabbit.niloapi.model.user.User;

public interface UserRepository extends JpaRepository<User, String> {

UserDetails findByUsername(String username);

}
