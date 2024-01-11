package game.you.dto;

import game.you.entity.ArticleEN;
import game.you.entity.Article_poster_urlsEN;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Setter
@Getter
public class ArticleDTOEN {
    private long id;
    private String title;
    private String des;
    private String seo_des;
    private String seo_title;
    private String mark;
    private Instant atCreate;
    private CategoryDTOEN category;
    private Article_poster_urlsDTOEN  posterUrls;
    private Set<TagDTOEN> tagSet;
    private GamePostforArticleDTOEN gamePost;
    private Set<CommentDTOEN> commentSet;
    private StatisticsArticleDTOEN statistics;
    private String url_post;

}
