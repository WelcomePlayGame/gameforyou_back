package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class ArticleDTOPL {
    private long id;
    private String title;
    private String des;
    private String seo_des;
    private String seo_title;
    private String mark;
    private Instant atCreate;
    private CategoryDTOPL category;
    private Article_poster_urlsDTOPL  posterUrls;
    private Set<TagDTOPL> tagSet;
    private GamePostforArticleDTOPL gamePost;
    private Set<CommentDTOPL> commentSet;
    private StatisticsArticleDTOPL statistics;
    private String url_post;
}
