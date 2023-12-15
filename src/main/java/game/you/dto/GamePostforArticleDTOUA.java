package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class GamePostforArticleDTOUA {
    private  long id;
    private String title;
    private String datatime;
    private GamePostVerticalDTOUA posterVertical_urs;
    private Set<PlatformsDTOUA> platformsSet;
    private Set<GenresDTOUA> genresSet;
    private String url_post;
}
