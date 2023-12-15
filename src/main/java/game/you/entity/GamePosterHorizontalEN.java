package game.you.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GamePosterHorizontalEN {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "poster_1024x768")
    private String poster_1024x768;
    @Column(name = "poster_480x320")
    private String poster_480x320;
    @OneToOne(mappedBy = "posterHorizontal_uls")
    private GamePostEN gamePost;

}
