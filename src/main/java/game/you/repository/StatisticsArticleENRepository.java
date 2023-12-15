package game.you.repository;

import game.you.entity.StatisticsArticleEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsArticleENRepository extends JpaRepository<StatisticsArticleEN, Long> {
}
