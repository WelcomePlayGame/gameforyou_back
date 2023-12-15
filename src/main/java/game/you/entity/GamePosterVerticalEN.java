package game.you.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GamePosterVerticalEN {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "poster_300x300")
    private String poster_300x300;
    @OneToOne(mappedBy = "posterVertical_urs")
    private GamePostEN gamePost;
}
