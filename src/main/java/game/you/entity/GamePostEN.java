package game.you.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

import org.hibernate.annotations.Type;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GamePostEN implements Serializable  {
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_horizontal", referencedColumnName = "id")
    private GamePosterHorizontalEN posterHorizontal_uls;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "poster_vertical", referencedColumnName = "id")
    private GamePosterVerticalEN posterVertical_urs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devoloper_id")
    private DeveloperGameEN developer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private PublisherEN publisher;

    @OneToMany(mappedBy = "gamePost")
    private Set<ArticleEN> articleSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "gamePost")
    private Set<CommentEN>  commentSet = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "gamepost_id")},
            inverseJoinColumns = {@JoinColumn(name = "platforms_id")}
    )
    private Set<PlatformsEN> platformsSet = new LinkedHashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "gamepost_id")},
            inverseJoinColumns = {@JoinColumn(name = "genres_id")}
    )
    private Set<GenresEN> genresSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "gamePost", fetch = FetchType.EAGER)
    private Set<GamePost_des_urlsEN> gamePost_des_urls;
}
