package game.you.repository;

import game.you.entity.GamePosterHorizontalEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePosterHorizontalRepositoryEN extends JpaRepository<GamePosterHorizontalEN, Long> {
}
