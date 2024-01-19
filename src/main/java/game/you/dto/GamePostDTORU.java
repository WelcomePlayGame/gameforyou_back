package game.you.dto;

import game.you.entity.GamePosterHorizontalRU;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class GamePostDTORU implements Serializable {
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
    private GamePostHorizontalDTORU posterHorizontal_uls;
    private GamePostVerticalDTORU posterVertical_urs;
    private DevoloperGameDTORU developer;
    private PublisherDTORU publisher;
    private Set<PlatformsDTORU> platformsSet;
    private Set<GenresDTORU> genresSet;
    private Set<GamePost_des_urlsDTORU>  gamePost_des_urls;
    private String url_post;
    private String series_games;
}
