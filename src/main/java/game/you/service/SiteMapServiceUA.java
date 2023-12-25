package game.you.service;

import game.you.entity.ArticleUA;
import game.you.entity.GamePostUA;
import game.you.repository.ArticleRepositoryUA;
import game.you.repository.GamePostRepositoryUA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SiteMapServiceUA {
    private final ArticleRepositoryUA repository_article;
    private final GamePostRepositoryUA repository_game;
    public List<String> getSiteMap() {
        List<String> sitemap = new ArrayList<>();
        sitemap.add("https://gameforyou.online/ua/");
        sitemap.add("https://gameforyou.online/ua/article");
        sitemap.add("https://gameforyou.online/ua/games");
        List<GamePostUA> listGame = repository_game.findAll();
        for (GamePostUA gamePostUA : listGame) {
            sitemap.add("https://gameforyou.online/ua/"+"games/"+gamePostUA.getUrl_post());
        }
        List<ArticleUA> listArticle = repository_article.findAll();
        for (ArticleUA articleUA : listArticle) {
            sitemap.add("https://gameforyou.online/ua/article/"+articleUA.getUrl_post());
        }
        return sitemap;
    }
}
