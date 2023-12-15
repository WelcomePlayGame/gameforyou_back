package game.you.repository;

import game.you.entity.GamePostEN;
import game.you.entity.GamePostPL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamePostRepositoryPL extends JpaRepository<GamePostPL, Long> {
    @Query("select g from GamePostPL g where g.url_post  = :id")
    Optional<GamePostPL> findByUrl(@Param("id") String id);
}
