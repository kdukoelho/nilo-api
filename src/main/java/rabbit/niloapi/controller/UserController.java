package rabbit.niloapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rabbit.niloapi.dto.user.UserRequestDTO;
import rabbit.niloapi.dto.user.UserResponseDTO;
import rabbit.niloapi.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> userResponseDTOList = this.service.findAll();
        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable String id){
        UserResponseDTO userResponseDTO = this.service.findById(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = this.service.create(userRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRequestDTO userRequestDTO, @PathVariable String id){
        this.service.update(userRequestDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
