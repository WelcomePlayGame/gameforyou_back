package game.you.repository;


import game.you.entity.GamePost_des_urlsEN;
import game.you.entity.GamePost_des_urlsRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePostDesUrlRepositoryRU extends JpaRepository<GamePost_des_urlsRU, Long> {
}
