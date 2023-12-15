package game.you.repository;


import game.you.entity.DeveloperGameEN;
import game.you.entity.DeveloperGamePL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevoloperRepositoryPL extends JpaRepository<DeveloperGamePL, Long> {
}
