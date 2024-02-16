package rabbit.niloapi.dto.user;

import rabbit.niloapi.model.user.User;

public record UserResponseDTO(String id, String username, String passwordHash) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getUsername(), user.getPasswordHash());
    }
}
