package game.you.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article_poster_urlsPL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "poster_url_480x320")
    @NotNull
    private String posterUrl480x320;

    @Column(name = "poster_url_1024x768")
    @NotNull
    private String posterUrl1024x768;


    @OneToOne(mappedBy = "posterUrls")
    private ArticlePL article;
}
