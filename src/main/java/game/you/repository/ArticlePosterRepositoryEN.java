package game.you.repository;

import game.you.entity.Article_poster_urlsEN;
import game.you.entity.Article_poster_urlsUA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlePosterRepositoryEN extends JpaRepository<Article_poster_urlsEN, Long> {
}
