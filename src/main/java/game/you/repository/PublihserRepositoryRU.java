package game.you.repository;

import game.you.entity.PublisherEN;
import game.you.entity.PublisherRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublihserRepositoryRU extends JpaRepository<PublisherRU, Long> {
}
