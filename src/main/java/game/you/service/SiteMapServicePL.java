package game.you.service;

import game.you.entity.ArticlePL;
import game.you.entity.GamePostPL;
import game.you.repository.ArticleRepositoryPL;
import game.you.repository.GamePostRepositoryPL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SiteMapServicePL {
    private final ArticleRepositoryPL repository_article;
    private final GamePostRepositoryPL repository_game;
    public List<String> getSiteMap() {
        List<String> sitemap = new ArrayList<>();
        sitemap.add("https://gameforyou.online/pl/");
        sitemap.add("https://gameforyou.online/pl/article");
        sitemap.add("https://gameforyou.online/pl/games");
        List<GamePostPL> listGame = repository_game.findAll();
        for (GamePostPL gamePostPL : listGame) {
            sitemap.add("https://gameforyou.online/pl/"+"games/"+gamePostPL.getUrl_post());
        }
        List<ArticlePL> listArticle = repository_article.findAll();
        for (ArticlePL articlePL : listArticle) {
            sitemap.add("https://gameforyou.online/pl/article/"+articlePL.getUrl_post());
        }
        return sitemap;
    }
}
