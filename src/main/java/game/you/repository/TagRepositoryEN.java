package game.you.repository;

import game.you.entity.TagEN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepositoryEN extends JpaRepository<TagEN, Long> {
}
