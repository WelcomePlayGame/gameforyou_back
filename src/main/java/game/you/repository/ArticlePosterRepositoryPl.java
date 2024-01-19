package game.you.repository;

import game.you.entity.Article_poster_urlsPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePosterRepositoryPl extends JpaRepository<Article_poster_urlsPL, Long> {
}
