package rabbit.niloapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbit.niloapi.dto.authentication.SignUpDTO;
import rabbit.niloapi.dto.authentication.LoginResponseDTO;
import rabbit.niloapi.dto.authentication.SignInDTO;
import rabbit.niloapi.model.user.User;
import rabbit.niloapi.repository.UserRepository;
import rabbit.niloapi.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SignInDTO signInDTO){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(signInDTO.username(), signInDTO.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid SignUpDTO signUpDTO){
        if (this.userRepository.findByUsername(signUpDTO.username()) != null) { return ResponseEntity.badRequest().build(); }

        String passwordHash = new BCryptPasswordEncoder().encode(signUpDTO.password());

        this.userRepository.save(new User(signUpDTO.username(), passwordHash, signUpDTO.role()));

        return ResponseEntity.ok().build();
    }
}
