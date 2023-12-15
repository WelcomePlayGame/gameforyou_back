package game.you.repository;

import game.you.entity.TagRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepositoryRU extends JpaRepository<TagRU, Long> {
}
