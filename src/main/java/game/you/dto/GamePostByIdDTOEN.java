package game.you.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import game.you.entity.ArticleEN;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class GamePostByIdDTOEN implements Serializable {
    private Long id;
    private String title;
    private String seo_title;
    private String des;
    private String seo_des;
    private String url_game;
    private String mark;
    private String datatime;
    private String os;
    private String minProcessor;
    private String maxProcessor;
    private String minRam;
    private String maxRam;
    private String directX;
    private String lan;
    private String memory;
    private GamePostHorizontalDTOEN posterHorizontal_uls;
    private GamePostVerticalDTOEN posterVertical_urs;
    private DevoloperGameDTOEN developer;
    private PublisherDTORU publisher;
    private Set<PlatformsDTOEN> platformsSet;
    private Set<GenresDTOEN> genresSet;
    private String url_post;
    private Set<ListArticleForGame> articleSet;
    private Set<CommentDTOEN> commentSet;
    private String series_games;
    private String revies_admin;
}
