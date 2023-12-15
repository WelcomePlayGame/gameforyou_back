package game.you.repository;


import game.you.entity.DeveloperGameUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevoloperRepositoryUA extends JpaRepository<DeveloperGameUA, Long> {
}
