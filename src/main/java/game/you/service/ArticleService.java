package game.you.service;

import game.you.entity.Article;
import game.you.entity.Category;
import game.you.entity.GamePost;
import game.you.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    final private ArticleRepository repository;

    @Transactional
    public Article addArticle(Article article) {
        Article addArticle = new Article();
        addArticle.setTitle(article.getTitle());
        addArticle.setDes(article.getDes());
        addArticle.setSeo_title(article.getSeo_title());
        addArticle.setSeo_des(article.getSeo_des());
        addArticle.setMark(article.getMark());
        addArticle.setAtCreate(Instant.now());
        addArticle.setPhoto_Url(article.getPhoto_Url());
        addArticle.setVideo_Url(article.getVideo_Url());
        addArticle.setCategory(article.getCategory());
        addArticle.setGamePost(article.getGamePost());

        addArticle.setTagSet(article.getTagSet());
        return repository.save(addArticle);
    }

    public List<Article> getListArticle() {
        return  repository.findAll();
    }
}
