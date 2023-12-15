package game.you.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GamePosterVerticalUA {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "poster_300x300")
    private String poster_300x300;
    @OneToOne(mappedBy = "posterVertical_urs")
    private GamePostUA gamePost;
}
