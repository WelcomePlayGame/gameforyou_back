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
    @Query("select  g from  GamePostEN  g where"+
            ":id is null  or g.id = :id")
    List<GamePostEN> findAllCustom(@Param("id") Long id);


    @Query("SELECT g FROM GamePostEN g LEFT JOIN FETCH g.articleSet LEFT JOIN FETCH g.commentSet WHERE g.url_post = :id")
    Optional<GamePostEN> findByUrlPost(String id);

}
