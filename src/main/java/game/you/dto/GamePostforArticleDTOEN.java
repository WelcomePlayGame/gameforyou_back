package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class GamePostforArticleDTOEN {
    private  long id;
    private String title;
    private String datatime;
    private GamePostVerticalDTOEN posterVertical_urs;
    private Set<PlatformsDTOEN> platformsSet;
    private Set<GenresDTOEN> genresSet;
    private String url_post;
}
