package game.you.repository;

import game.you.entity.StatisticsArticleEN;
import game.you.entity.StatisticsArticlePL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsArticlePLRepository extends JpaRepository<StatisticsArticlePL, Long> {
}
