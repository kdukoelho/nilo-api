package rabbit.niloapi.build.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = User.ENTITY_NAME) @Table(name = User.TABLE_NAME)
@RequiredArgsConstructor @NoArgsConstructor
@Data
public class User {
    public static final String TABLE_NAME = "tb_users";
    public static final String ENTITY_NAME = "user";

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
}
