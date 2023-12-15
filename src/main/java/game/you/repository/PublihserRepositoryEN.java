package game.you.repository;

import game.you.entity.PublisherEN;
import game.you.entity.PublisherUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublihserRepositoryEN extends JpaRepository<PublisherEN, Long> {
}
