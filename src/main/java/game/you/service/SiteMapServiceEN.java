package game.you.service;

import game.you.entity.ArticleEN;
import game.you.entity.GamePostEN;
import game.you.repository.ArticleRepositoryEN;
import game.you.repository.GamePostRepositoryEN;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SiteMapServiceEN {
    private final ArticleRepositoryEN repository_article;
    private final GamePostRepositoryEN repository_game;
    public List<String> getSiteMap() {
    List<String> sitemap = new ArrayList<>();
    sitemap.add("https://gameforyou.online/en/");
    sitemap.add("https://gameforyou.online/en/article");
    sitemap.add("https://gameforyou.online/en/games");
    List<GamePostEN> listGame = repository_game.findAll();
    for (GamePostEN gamePostEN : listGame) {
        sitemap.add("https://gameforyou.online/en/"+"games/"+gamePostEN.getUrl_post());
    }
    List<ArticleEN> listArticle = repository_article.findAll();
    for (ArticleEN articleEN : listArticle) {
        sitemap.add("https://gameforyou.online/en/article/"+articleEN.getUrl_post());
    }
    return sitemap;
    }
}
