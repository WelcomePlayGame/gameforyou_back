package game.you.repository;

import game.you.entity.GamePostEN;
import game.you.entity.GamePostUA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamePostRepositoryEN extends JpaRepository<GamePostEN, Long> {
    @Query("SELECT g FROM GamePostEN g WHERE (:id IS NULL OR g.id = :id) AND (:series_games IS NULL OR (g.series_games) = (:series_games))")
    List<GamePostEN> findAllCustom(@Param("id") Long id, @Param("series_games") String series_games);




    @Query("SELECT g FROM GamePostEN g LEFT JOIN FETCH g.articleSet LEFT JOIN FETCH g.commentSet WHERE g.url_post = :id")
    Optional<GamePostEN> findByUrlPost(String id);

}
