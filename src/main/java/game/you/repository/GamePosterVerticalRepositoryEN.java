package game.you.repository;

import game.you.entity.GamePosterVerticalEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePosterVerticalRepositoryEN extends JpaRepository<GamePosterVerticalEN, Long> {
}
