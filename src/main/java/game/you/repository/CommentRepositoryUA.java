package game.you.repository;

import game.you.entity.CommentUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryUA extends JpaRepository<CommentUA, Long> {
}
