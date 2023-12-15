package game.you.repository;

import game.you.entity.TagUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepositoryUA extends JpaRepository<TagUA, Long> {
}
