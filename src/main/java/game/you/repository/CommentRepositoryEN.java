package game.you.repository;

import game.you.entity.CommentEN;
import game.you.entity.CommentUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryEN extends JpaRepository<CommentEN, Long> {
}
