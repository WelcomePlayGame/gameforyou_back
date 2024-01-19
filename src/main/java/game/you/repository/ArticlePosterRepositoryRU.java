package game.you.repository;

import game.you.entity.Article_poster_urlsRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePosterRepositoryRU extends JpaRepository<Article_poster_urlsRU, Long> {
}
