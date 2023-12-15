package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class GamePostforArticleDTOPL {
    private  long id;
    private String title;
    private String datatime;
    private GamePostVerticalDTOPL posterVertical_urs;
    private Set<PlatformsDTOPL> platformsSet;
    private Set<GenresDTOPL> genresSet;
    private String url_post;
}