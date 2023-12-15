package game.you.repository;

import game.you.entity.Article_des_urlsEN;
import game.you.entity.Article_des_urlsPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDesUrlsRepositoryPL extends JpaRepository<Article_des_urlsPL, Long> {
}
