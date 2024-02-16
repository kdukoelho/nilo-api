package rabbit.niloapi.dto.authentication;

import rabbit.niloapi.model.user.UserRole;

public record SignUpDTO(String username, String password, UserRole role) {
}
