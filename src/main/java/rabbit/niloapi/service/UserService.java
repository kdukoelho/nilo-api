package rabbit.niloapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabbit.niloapi.dto.user.UserRequestDTO;
import rabbit.niloapi.dto.user.UserResponseDTO;
import rabbit.niloapi.model.User;
import rabbit.niloapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserResponseDTO> findAll(){
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        this.repository.findAll().forEach(user -> userResponseDTOList.add(new UserResponseDTO(user)));
        return userResponseDTOList;
    }

    public UserResponseDTO findById(String id){
        Optional<User> user = this.repository.findById(id);
        return new UserResponseDTO(
                user.orElseThrow(() -> new RuntimeException(
                        String.format("User id %s not found.", id))));
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequestDTO){
        User user = new User(userRequestDTO);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(userRequestDTO.password()));
        this.repository.save(user);
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO userRequestDTO, String id){
        User user = new User(userRequestDTO);
        user.setId(id);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(userRequestDTO.password()));
        this.repository.save(user);
        return new UserResponseDTO(user);
    }

    @Transactional
    public String deleteById(String id){
        this.repository.deleteById(id);
        return "Transaction completed";
    }
}
