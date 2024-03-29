package rabbit.niloapi.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rabbit.niloapi.dto.user.UserRequestDTO;
import rabbit.niloapi.model.Entry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = User.ENTITY_NAME) @Table(name = User.TABLE_NAME)
@RequiredArgsConstructor
@Data
public class User implements UserDetails {
    public static final String TABLE_NAME = "tb_users";
    public static final String ENTITY_NAME = "user";

    public User(UserRequestDTO userRequestDTO){
        this.username = userRequestDTO.username();
        this.role = userRequestDTO.userRole();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String id;

    @Column(name = "username", length = 80)
    @Size(max = 80)
    private String username;

    @Column(name = "password_hash", length = 100)
    @Size(max = 100)
    private String passwordHash;

    @OneToMany(mappedBy = "user")
    private List<Entry> entriesList = new ArrayList<>();

    private UserRole role;

    public User(String username, String passwordHash, UserRole role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
