package game.you.repository;

import game.you.entity.Article_poster_urlsUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePosterRepositoryUA  extends JpaRepository<Article_poster_urlsUA, Long> {
}
