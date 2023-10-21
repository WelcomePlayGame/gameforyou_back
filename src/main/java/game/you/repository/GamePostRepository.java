package game.you.repository;

import game.you.entity.GamePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePostRepository extends JpaRepository<GamePost, Long> {
}
