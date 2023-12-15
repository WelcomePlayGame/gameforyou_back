package game.you.repository;

import game.you.entity.CommentEN;
import game.you.entity.CommentRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryRU extends JpaRepository<CommentRU, Long> {
}
