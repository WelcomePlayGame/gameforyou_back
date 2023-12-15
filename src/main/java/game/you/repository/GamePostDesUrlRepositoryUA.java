package game.you.repository;


import game.you.entity.GamePost_des_urlsUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePostDesUrlRepositoryUA extends JpaRepository<GamePost_des_urlsUA, Long> {
}
