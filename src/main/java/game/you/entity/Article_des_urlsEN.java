package game.you.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article_des_urlsEN {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEN article;
}
