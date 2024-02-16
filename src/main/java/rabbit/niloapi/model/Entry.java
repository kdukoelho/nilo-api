package rabbit.niloapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import rabbit.niloapi.dto.entry.EntryRequestDTO;
import rabbit.niloapi.model.user.User;

@Entity(name = Entry.ENTITY_NAME) @Table(name = Entry.TABLE_NAME)
@NoArgsConstructor(force = true)
@Data
public class Entry {
    public static final String TABLE_NAME = "tb_entries";
    public static final String ENTITY_NAME = "entry";

    public Entry(EntryRequestDTO entryRequestDTO){
        this.title = entryRequestDTO.title();
        this.url = entryRequestDTO.url();
        this.login = entryRequestDTO.login();
        this.password = entryRequestDTO.password();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String id;

    @NotBlank
    @Column(name="title", unique = true, length = 30)
    @Size(max=30)
    private String title;

    @Column(name = "url", length = 120)
    @Size(min = 0, max = 120)
    private String url;

    @Column(name = "login", length = 80)
    @Size(max = 80)
    private String login;

    @Column(name = "password", length = 100)
    @Size(max = 100)
    private String password;

    @ManyToOne
    private User user;

}
