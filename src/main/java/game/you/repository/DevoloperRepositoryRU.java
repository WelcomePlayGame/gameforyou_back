package game.you.repository;


import game.you.entity.DeveloperGameEN;
import game.you.entity.DeveloperGameRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevoloperRepositoryRU extends JpaRepository<DeveloperGameRU, Long> {
}
