package game.you.repository;

import game.you.entity.Article_des_urlsEN;
import game.you.entity.Article_des_urlsRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDesUrlsRepositoryRU extends JpaRepository<Article_des_urlsRU, Long> {
}
