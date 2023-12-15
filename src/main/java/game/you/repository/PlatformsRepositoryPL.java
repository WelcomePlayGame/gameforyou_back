package game.you.repository;

import game.you.entity.PlatformsEN;
import game.you.entity.PlatformsPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformsRepositoryPL extends JpaRepository<PlatformsPL, Long> {

}
