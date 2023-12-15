package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class ArticleDTORU {
    private long id;
    private String title;
    private String des;
    private String seo_des;
    private String seo_title;
    private String mark;
    private Instant atCreate;
    private CategoryDTORU category;
    private Article_poster_urlsDTORU  posterUrls;
    private Set<TagDTORU> tagSet;
    private GamePostforArticleDTORU gamePost;
    private Set<CommentDTORU> commentSet;
    private StatisticsArticleDTORU statistics;
    private String url_post;
}
