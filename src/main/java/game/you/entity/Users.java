package game.you.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@RestResource(rel = "user", path = "user")
 public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "username")
    private String userName;
    @Column(name = "userpassword")
    private char [] userPassword;
    @Column(name = "role")
    private String Role;

}
