package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class GamePostDTOEN implements Serializable {
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
    private String series_games;
}
