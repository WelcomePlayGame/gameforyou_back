package game.you.repository;

import game.you.entity.StatisticsArticlePL;
import game.you.entity.StatisticsArticleUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsArticleUARepository extends JpaRepository<StatisticsArticleUA, Long> {
}
