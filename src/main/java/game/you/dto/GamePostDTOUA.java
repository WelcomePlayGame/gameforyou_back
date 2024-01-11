package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class GamePostDTOUA implements Serializable {
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
    private GamePostHorizontalDTOUA posterHorizontal_uls;
    private GamePostVerticalDTOUA posterVertical_urs;
    private DevoloperGameDTOUA developer;
    private PublisherDTOUA publisher;
    private Set<PlatformsDTOUA> platformsSet;
    private Set<GenresDTOUA> genresSet;
    private Set<GamePost_des_urlsDTOUA>  gamePost_des_urls;
    private String url_post;
}
