package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class ListArticleForGame {
    private long id;
    private String title;
    private Article_poster_urlsDTOEN  posterUrls;
    private Set<TagDTOEN> tagSet;
    private Instant atCreate;
    private String url_post;
    private Set<CommentDTOEN> commentSet;
}
