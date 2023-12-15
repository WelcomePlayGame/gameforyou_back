package game.you.repository;

import game.you.entity.PublisherPL;
import game.you.entity.PublisherUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublihserRepositoryPL extends JpaRepository<PublisherPL, Long> {
}
