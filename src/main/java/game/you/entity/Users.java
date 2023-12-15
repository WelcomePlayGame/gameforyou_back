package game.you.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

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

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "prefence_id", referencedColumnName = "id")
   private UserLanguage userLanguage;
}
