package game.you.repository;

import game.you.entity.GamePostRU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamePostRepositoryRU extends JpaRepository<GamePostRU, Long> {
   @Query("select g from GamePostRU g where g.url_post = :id")
    Optional<GamePostRU> findByUrl(@Param("id") String id);
}
