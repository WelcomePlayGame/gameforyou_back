package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class ArticleDTOUA {
    private long id;
    private String title;
    private String des;
    private String seo_des;
    private String seo_title;
    private String mark;
    private Instant atCreate;
    private CategoryDTOUA category;
    private Article_poster_urlsDTOUA  posterUrls;
    private Set<TagDTOUA> tagSet;
    private GamePostforArticleDTOUA gamePost;
    private Set<CommentDTOUA> commentSet;
    private StatisticsArticleDTOUA statistics;
    private String url_post;

}
