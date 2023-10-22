package game.you.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "des")
    private String des;
    @Column(name = "seo_des")
    private String seo_des;
    @Column(name = "seo_title")
    private String seo_title;
    @Column(name = "mark")
    private String mark;
    @Column(name = "data")
    private Instant atCreate;
    @Column(name = "photo_Url")
    private String photo_Url;
    @Column(name = "video_Url")
    private String video_Url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gamepost_id", nullable = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private GamePost gamePost;

    @ManyToMany
    @JoinTable(
            name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<Tag> tagSet = new LinkedHashSet<>();

}
