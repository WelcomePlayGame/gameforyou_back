package game.you.repository;

import game.you.entity.CommentEN;
import game.you.entity.CommentPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryPL extends JpaRepository<CommentPL, Long> {
}
