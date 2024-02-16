package rabbit.niloapi.dto.user;

import rabbit.niloapi.model.user.UserRole;

public record UserRequestDTO(String id, String username, String password, UserRole userRole){
}
