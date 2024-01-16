package game.you.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEN implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", unique = true)
    @NotEmpty
    @Size(min = 4, max = 75, message = "title must be from 4 to 75 symbol")
    private String title;
    @Column(name = "des", columnDefinition = "TEXT")
    @NotEmpty
    private String des;
    @Column(name = "seo_des")
    @NotEmpty
    private String seo_des;
    @Column(name = "seo_title", unique = true)
    @NotEmpty
    private String seo_title;
    @Column(name = "mark")
    private String mark;
    @Column(name = "data")
    private Instant atCreate;
    @Column(name = "url_post")
    private String url_post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_urls_id", referencedColumnName = "id")
    private Article_poster_urlsEN posterUrls;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id", referencedColumnName = "id")
    private StatisticsArticleEN statistics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEN category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gamepost_id")
    private GamePostEN gamePost;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEN> tagSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Article_des_urlsEN> article_des_urls;
    @OneToMany(mappedBy = "articleEN", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<CommentEN> commentSet;
}
