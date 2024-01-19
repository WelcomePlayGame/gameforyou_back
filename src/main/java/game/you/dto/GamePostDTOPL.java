package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class GamePostDTOPL implements Serializable {
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
    private GamePostHorizontalDTOPL posterHorizontal_uls;
    private GamePostVerticalDTOPL posterVertical_urs;
    private DevoloperGameDTOPL developer;
    private PublisherDTOPL publisher;
    private Set<PlatformsDTOPL> platformsSet;
    private Set<GenresDTOPL> genresSet;
    private Set<GamePost_des_urlsDTOPL>  gamePost_des_urls;
    private String url_post;
    private String series_games;
}
