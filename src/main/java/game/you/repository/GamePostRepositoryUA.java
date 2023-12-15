package game.you.repository;

import game.you.entity.GamePostUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamePostRepositoryUA extends JpaRepository<GamePostUA, Long> {
    @Query("select g from GamePostUA  g where g.url_post = :id")
    Optional<GamePostUA> findByUrl(@Param("id") String id);
}
