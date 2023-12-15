package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class GamePostforArticleDTORU {
    private  long id;
    private String title;
    private String datatime;
    private GamePostVerticalDTORU posterVertical_urs;
    private Set<PlatformsDTORU> platformsSet;
    private Set<GenresDTORU> genresSet;
    private String url_post;
}
