package game.you.repository;

import game.you.entity.PlatformsUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformsRepositoryUA extends JpaRepository<PlatformsUA, Long> {

}
