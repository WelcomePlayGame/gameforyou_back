package game.you.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEN {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title_comment")
    private String title_comment;
    @Column(name = "des_comment")
    private String des_comment;
    @ElementCollection
    @Column(name = "positiveInputs")
    private Set<String> positiveInputs;
    @ElementCollection
    @Column(name = "negativeInputs")
    private Set<String> negativeInputs;
    @Column(name = "rating")
    private long rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEN articleEN;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GamePostEN gamePost;

}
