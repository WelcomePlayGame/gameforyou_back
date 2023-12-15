package game.you.repository;

import game.you.entity.PublisherUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublihserRepositoryUA extends JpaRepository<PublisherUA, Long> {
}
