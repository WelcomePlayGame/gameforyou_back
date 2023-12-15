package game.you.repository;

import game.you.entity.PlatformsEN;
import game.you.entity.PlatformsRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformsRepositoryRU extends JpaRepository<PlatformsRU, Long> {

}
