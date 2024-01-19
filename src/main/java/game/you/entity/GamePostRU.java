package game.you.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GamePostRU implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "seo_title")
    private String seo_title;
    @Column(name = "des", columnDefinition = "TEXT")
    private String des;
    @Column(name = "seo_des")
    private String seo_des;
    @Column(name = "url_game")
    private String url_game;
    @Column(name = "mark")
    private String mark;
    @Column(name = "datatime")
    private String datatime;
    @Column(name = "os")
    private String os;
    @Column(name = "minProcessor")
    private String minProcessor;
    @Column(name = "maxProcessor")
    private String maxProcessor;
    @Column(name = "minRam")
    private String minRam;
    @Column(name = "maxRam")
    private String maxRam;
    @Column(name = "directX")
    private String directX;
    @Column(name = "lan")
    private String lan;
    @Column(name = "memory")
    private String memory;
    @Column(name = "url_post")
    private String url_post;
    @Column(name = "series_games")
    private String series_games;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_horizontal", referencedColumnName = "id")
    private GamePosterHorizontalRU posterHorizontal_uls;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_vertical", referencedColumnName = "id")
    private GamePosterVerticalRU posterVertical_urs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devoloper_id")
    private DeveloperGameRU developer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherRU publisher;

    @OneToMany(mappedBy = "gamePost")
    private Set<ArticleRU> getArticleList = new LinkedHashSet<>();


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "gamepost_id")},
            inverseJoinColumns = {@JoinColumn(name = "platforms_id")}
    )
    private Set<PlatformsRU> platformsSet = new LinkedHashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "gamepost_id")},
            inverseJoinColumns = {@JoinColumn(name = "genres_id")}
    )
    private Set<GenresRU> genresSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "gamePost", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GamePost_des_urlsRU> gamePost_des_urls;
}
