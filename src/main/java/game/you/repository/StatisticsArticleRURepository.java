package game.you.repository;

import game.you.entity.StatisticsArticleEN;
import game.you.entity.StatisticsArticleRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsArticleRURepository extends JpaRepository<StatisticsArticleRU, Long> {
}
