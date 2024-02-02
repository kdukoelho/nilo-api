package rabbit.niloapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity(name = User.ENTITY_NAME) @Table(name = Entry.TABLE_NAME)
@RequiredArgsConstructor @NoArgsConstructor(force = true)
@Data
public class Entry {
    public static final String TABLE_NAME = "tb_entries";
    public static final String ENTITY_NAME = "entry";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String id;

    @NotBlank
    @Column(name="title", unique = true, length = 30)
    @Size(max=30)
    private final String title;

    @Column(name = "url", length = 120)
    @Size(min = 0, max = 120)
    private final String url;

    @Column(name = "login", length = 80)
    @Size(max = 80)
    private final String login;

    @Column(name = "password", length = 100)
    @Size(max = 100)
    private final String password;

    @ManyToOne
    private User user;

}
