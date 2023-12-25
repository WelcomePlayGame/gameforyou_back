package game.you.service;

import game.you.entity.ArticleEN;
import game.you.entity.ArticleRU;
import game.you.entity.GamePostEN;
import game.you.entity.GamePostRU;
import game.you.repository.ArticleRepositoryEN;
import game.you.repository.ArticleRepositoryRU;
import game.you.repository.GamePostRepositoryEN;
import game.you.repository.GamePostRepositoryRU;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SiteMapServiceRU {
    private final ArticleRepositoryRU repository_article;
    private final GamePostRepositoryRU repository_game;
    public List<String> getSiteMap() {
        List<String> sitemap = new ArrayList<>();
        sitemap.add("https://gameforyou.online/ru/");
        sitemap.add("https://gameforyou.online/ru/article");
        sitemap.add("https://gameforyou.online/ru/games");
        List<GamePostRU> listGame = repository_game.findAll();
        for (GamePostRU gamePostRU : listGame) {
            sitemap.add("https://gameforyou.online/ru/"+"games/"+gamePostRU.getUrl_post());
        }
        List<ArticleRU> listArticle = repository_article.findAll();
        for (ArticleRU articleRU : listArticle) {
            sitemap.add("https://gameforyou.online/ru/article/"+articleRU.getUrl_post());
        }
        return sitemap;
    }
}
