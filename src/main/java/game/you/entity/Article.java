package game.you.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
@RestResource(rel = "article", path = "article")
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
    @Column(name = "tag")
    private String tag;
    @Column(name = "data")
    private Instant atCreate;
    @Column(name = "photo_Url")
    private String photo_Url;
    @Column(name = "video_Url")
    private String video_Url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
